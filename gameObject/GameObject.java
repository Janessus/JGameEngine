package gameObject;
import java.awt.Graphics;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import core.Game;
import templates.GameShape;
import gameObject.components.GameObjectComponent;

public abstract class GameObject
{
	private GameShape shape;
	private Game game;
	
	ArrayList<GameObjectComponent> objectComponents;
	
	protected GameObject(Game game, GameShape shape)
	{
		this.shape = shape;
		this.game = game;
	}


	public void paint(Graphics g)
	{
		paintObject(g);
	}
	
	
	public void paintObject(Graphics g)
	{
		shape.paint(g);
		
		for(int i = 0; i < objectComponents.size(); i++)
		{
			objectComponents.get(i).drawComponent(g);
		}
	}

	
	public Point2D getPosition()
	{
		return shape.getPosition();
	}

	
	public void addComponent(GameObjectComponent c)
	{
		if(objectComponents == null)
			objectComponents = new ArrayList<GameObjectComponent>();
		objectComponents.add(c);
	}
	
	
	public void removeComponent(GameObjectComponent c)
	{
		if(objectComponents != null)
			objectComponents.remove(c);
	}
	
	
	public GameObjectComponent getFirstComponent(Class type)
	{
		ArrayList<GameObjectComponent> c = getComponentList(type);
		if(c != null)
			return c.get(0);
		else return null;
	}
	

	public ArrayList<GameObjectComponent> getComponentList(Class type)
	{
		if(objectComponents == null)
			return null;
		
		ArrayList<GameObjectComponent> results = new ArrayList<GameObjectComponent>();
		
		for(GameObjectComponent goc : objectComponents)
		{
			if(goc.getClass().equals(type) || Arrays.asList(goc.getClass().getInterfaces()).contains(type))
			{
				results.add(goc);
			}
		}
		if(results.isEmpty())
			return null;
		
		return results;
	}

	
	public void updateObject()
	{
		if(objectComponents != null)
			for(int i = 0; i < objectComponents.size(); i++)
			{
				objectComponents.get(i).updateComponent();
			}
	}
	
	
	public void destroy()
	{
		if(objectComponents != null)
		{
			for(int i = 0; i < objectComponents.size(); i++)
			{
				objectComponents.remove(objectComponents.get(i));
			}
		}
		game.getGameObjects().remove(this);
	}
	
	
	public GameShape getShape()
	{
		return shape;
	}


	public Game getGame()
	{
		return game;
	}


	public ArrayList<GameObjectComponent> getObjectComponents()
	{
		return objectComponents;
	}

	
}
