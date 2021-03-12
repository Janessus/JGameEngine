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
	
	GameObject(MyShape shape)
	{
		this.shape=shape;
	}
	
	public void paint(Graphics g)
	{
		shape.paint(g);
	}

	@Override
	public Point getPosition()
	{
		return shape.getPosition();
	}

	@Override
	public void paintObject(Graphics g)
	{
		this.paint(g);
	}

	@Override
	public void addComponent(IGameObjectComponent c)
	{
		components.add(c);
	}
	

	public ArrayList<IGameObjectComponent> getComponents(Class type)
	{
		ArrayList<IGameObjectComponent> results = new ArrayList<IGameObjectComponent>();
		
		for(IGameObjectComponent goc : components)
		{
			if(goc.getClass().isInstance(type))
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
		for(IGameObjectComponent c : components)
		{
			c.update();
		}
	}
}
