package userSpace.controllers;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import gameObject.GameObject;
import gameObject.components.CombatComponent;
import gameObject.components.GameObjectComponent;
import templates.Direction;
import userSpace.players.Player;

public class ArtificialPlayerController extends GameObjectComponent
{
	private GameObject parent, target;
	
	private double speed = 0.4;
	private double minDistance;
	private int maxDetectionDistance;
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
		
		
		for(int i = 0; i < parent.getGame().getGameObjects().size(); i++)
		{
			tmp = parent.getGame().getGameObjects().get(i);
			tmpDistance = tmp.getShape().getCenterDistance(parent.getShape());
			
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
			return Direction.getDirection(parent.getShape().getCenter(), o.getShape().getCenter());
		return null;
	}
	
	
	@Override
	public void updateComponent()
	{
		double elapsedTime;
		Direction targetDirection;

		target = findClosestTarget();
		
		try {
			elapsedTime = 1_000_000_000 / parent.getGame().getFps(); //in nanoseconds
		} 
		catch (Exception e) 
		{
			elapsedTime = 0;
		}
		
		//follow target
		if((targetDirection = getTargetDirection(target)) != null)
		{	
			double distanceFactor = (speed * elapsedTime)/1_000_000;
			if(parent.getShape().getCircleEdgeDistance(target.getShape()) > minDistance)
				parent.getShape().translate(distanceFactor * targetDirection.getX(), distanceFactor * targetDirection.getY());
			
			((CombatComponent)parent.getFirstComponent(CombatComponent.class)).attack(targetDirection);
		}
	}

	
	@Override
	public void drawComponent(Graphics g)
	{
		if(visible)
		{
			//Viewing range
			g.setColor(Color.cyan);
			g.drawOval((int)(parent.getShape().getPosition().getX() - maxDetectionDistance + parent.getShape().getSize().getWidth()/2), 
					(int)(parent.getShape().getPosition().getY() - maxDetectionDistance + parent.getShape().getSize().getHeight()/2), 
					maxDetectionDistance*2, 
					maxDetectionDistance*2);
		
			//Min distance
			g.setColor(Color.pink);
			g.drawOval((int)(parent.getShape().getPosition().getX() - (minDistance + parent.getShape().getRadius()) + parent.getShape().getSize().getWidth()/2), 
					(int)(parent.getShape().getPosition().getY() - (minDistance + parent.getShape().getRadius()) + parent.getShape().getSize().getHeight()/2), 
					(int)(minDistance + parent.getShape().getRadius())*2, 
					(int)(minDistance + parent.getShape().getRadius())*2);
		}
	}

	
	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
}
