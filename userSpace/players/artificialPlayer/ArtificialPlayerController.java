package userSpace.players.artificialPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import core.Direction;
import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import gameObject.components.combat.CombatComponent;
import userSpace.players.humanPlayer.Player;

public class ArtificialPlayerController extends GameObjectComponent
{
	private GameObject target;
	
	private double speed = 0.4;
	private double minDistance;
	private int maxDetectionDistance;
	
	
	public ArtificialPlayerController(GameObject parent)
	{
		super(parent);
		maxDetectionDistance = 400;
		minDistance = 20;
	}
	
	
	public GameObject findClosestTarget()
	{
		GameObject closestTarget = null;
		GameObject tmp;
		double closestDistance = Double.MAX_VALUE;
		double tmpDistance = Double.MAX_VALUE;
		
		
		for(int i = 0; i < getParent().getGame().getGameObjects().size(); i++)
		{
			tmp = getParent().getGame().getGameObjects().get(i);
			tmpDistance = tmp.getShape().getCenterDistance(getParent().getShape());
			
			if(tmp.getClass().equals(Player.class))
			{
				if(closestTarget == null)
				{
					closestTarget = tmp;
					closestDistance = tmpDistance;
					continue;
				}
				if(tmpDistance < closestDistance)
				{
					closestTarget = tmp;
					closestDistance = tmpDistance;
				}
			}
		}
		if(closestDistance <= maxDetectionDistance)
			return closestTarget;
		return null;
	}

	
	private Direction getTargetDirection(GameObject o)
	{
		if(o != null)
			return Direction.getDirection(getParent().getShape().getCenter(), o.getShape().getCenter());
		return null;
	}
	
	
	@Override
	public void updateComponent()
	{
		double elapsedTime;
		Direction targetDirection;

		target = findClosestTarget();
		
		try {
			elapsedTime = 1_000_000_000 / getParent().getGame().getFPS(); //in nanoseconds
		} 
		catch (Exception e) 
		{
			elapsedTime = 0;
		}
		
		//follow target
		if((targetDirection = getTargetDirection(target)) != null)
		{	
			double distanceFactor = (speed * elapsedTime)/1_000_000;
			if(getParent().getShape().getCircleEdgeDistance(target.getShape()) > minDistance)
				getParent().getShape().translate(distanceFactor * targetDirection.getX(), distanceFactor * targetDirection.getY());
			
			((CombatComponent)getParent().getFirstComponent(CombatComponent.class)).attack(targetDirection);
		}
	}

	
	@Override
	public void drawComponent(Graphics2D g)
	{
		if(isVisible())
		{
			//Viewing range
			g.setColor(Color.cyan);
			g.drawOval((int)(getParent().getShape().getPosition().getX() - maxDetectionDistance + getParent().getShape().getSize().getWidth()/2), 
					(int)(getParent().getShape().getPosition().getY() - maxDetectionDistance + getParent().getShape().getSize().getHeight()/2), 
					maxDetectionDistance*2, 
					maxDetectionDistance*2);
		
			//Min distance
			g.setColor(Color.pink);
			g.drawOval((int)(getParent().getShape().getPosition().getX() - (minDistance + getParent().getShape().getRadius()) + getParent().getShape().getSize().getWidth()/2), 
					(int)(getParent().getShape().getPosition().getY() - (minDistance + getParent().getShape().getRadius()) + getParent().getShape().getSize().getHeight()/2), 
					(int)(minDistance + getParent().getShape().getRadius())*2, 
					(int)(minDistance + getParent().getShape().getRadius())*2);
		}
	}
}
