import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameObject extends JPanel implements IGameObject
{
	public MyShape shape;
	public boolean render = true;
	public boolean collide = true;
	ArrayList<IGameObjectComponent> objectComponents;
	
	GameObject(MyShape shape)
	{
		this.shape=shape;
	}
	
	@Override
	public void paint(Graphics g)
	{
		paintObject(g);
	}
	
	@Override
	public Point getPosition()
	{
		return shape.getPosition();
	}

	@Override
	public void paintObject(Graphics g)
	{
		shape.paint(g);
	}

	@Override
	public void addComponent(IGameObjectComponent c)
	{
		if(objectComponents == null)
			objectComponents = new ArrayList<IGameObjectComponent>();
		objectComponents.add(c);
	}
	

	public ArrayList<IGameObjectComponent> getComponents(Class type)
	{
		if(objectComponents == null)
			return null;
		
		ArrayList<IGameObjectComponent> results = new ArrayList<IGameObjectComponent>();
		
		for(IGameObjectComponent goc : objectComponents)
		{
			if(goc.getClass().equals(type))
			{
				results.add(goc);
			}
		}
		if(results.isEmpty())
			return null;
		
		return results;
	}

	@Override
	public void updateObject()
	{
		if(objectComponents != null)
			for(IGameObjectComponent c : objectComponents)
			{
				c.updateComponent();
			}
	}
}
