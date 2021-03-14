import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class GameObject extends JPanel
{
	public GameShape shape;
	public Game game;
	
	ArrayList<IGameObjectComponent> objectComponents;
	
	GameObject(Game game, GameShape shape)
	{
		this.shape = shape;
		this.game = game;
	}

	@Override
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

	
	public Point getPosition()
	{
		return shape.getPosition();
	}

	
	public void addComponent(IGameObjectComponent c)
	{
		if(objectComponents == null)
			objectComponents = new ArrayList<IGameObjectComponent>();
		objectComponents.add(c);
	}
	
	
	public void removeComponent(IGameObjectComponent c)
	{
		if(objectComponents != null)
			objectComponents.remove(c);
	}
	
	
	public IGameObjectComponent getFirstComponent(Class type)
	{
		return getComponentList(type).get(0);
	}
	

	public ArrayList<IGameObjectComponent> getComponentList(Class type)
	{
		if(objectComponents == null)
			return null;
		
		ArrayList<IGameObjectComponent> results = new ArrayList<IGameObjectComponent>();
		
		
		for(IGameObjectComponent goc : objectComponents)
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
		game.gameObjects.remove(this);
	}
}
