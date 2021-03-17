import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class ArtificialPlayerController implements IGameObjectComponent
{
	private GameObject parent, target;
	
	public double speed = 0.4;
	public double minDistance;
	int maxDetectionDistance;

	private boolean visible;
	
	
	public ArtificialPlayerController(GameObject parent)
	{
		this.parent = parent;
		maxDetectionDistance = 400;
		minDistance = 20;
	}
	
	
	public GameObject findClosestTarget()
	{
		GameObject closestTarget = null;
		GameObject tmp;
		double closestDistance = Double.MAX_VALUE;
		double tmpDistance = Double.MAX_VALUE;
		
		
		for(int i = 0; i < parent.game.gameObjects.size(); i++)
		{
			tmp = parent.game.gameObjects.get(i);
			tmpDistance = tmp.shape.getCenterDistance(parent.shape);
			
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
			return Direction.getDirection(parent.shape.getCenter(), o.shape.getCenter());
		
		return null;
	}
	
	
	@Override
	public void updateComponent()
	{
		double elapsedTime;
		
		Direction targetDirection;

		target = findClosestTarget();
		
		try {
			elapsedTime = 1_000_000_000 / parent.game.fps; //in nanoseconds
		} 
		catch (Exception e) 
		{
			elapsedTime = 0;
		}
		
		//follow target
		if((targetDirection = getTargetDirection(target)) != null)
		{	
			double distanceFactor = (speed * elapsedTime)/1_000_000;
			if(parent.shape.getCircleEdgeDistance(target.shape) > minDistance)
				parent.shape.translate(distanceFactor * targetDirection.getX(), distanceFactor * targetDirection.getY());
			
			((CombatComponent)parent.getFirstComponent(CombatComponent.class)).attack(targetDirection);
		}
	}

	
	@Override
	public void drawComponent(Graphics g)
	{
		//Viewing range
		g.setColor(Color.cyan);
		g.drawOval((int)(parent.shape.getPosition().getX() - maxDetectionDistance + parent.shape.getSize().getWidth()/2), 
				(int)(parent.shape.getPosition().getY() - maxDetectionDistance + parent.shape.getSize().getHeight()/2), 
				maxDetectionDistance*2, 
				maxDetectionDistance*2);
	
		//Min distance
		g.setColor(Color.pink);
		g.drawOval((int)(parent.shape.getPosition().getX() - (minDistance + parent.shape.getRadius()) + parent.shape.getSize().getWidth()/2), 
				(int)(parent.shape.getPosition().getY() - (minDistance + parent.shape.getRadius()) + parent.shape.getSize().getHeight()/2), 
				(int)(minDistance + parent.shape.getRadius())*2, 
				(int)(minDistance + parent.shape.getRadius())*2);
	}

	
	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
}
