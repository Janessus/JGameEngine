package gameObject.components;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import gameObject.GameObject;

public class Collider implements IGameObjectComponent
{
	private Rectangle2D collider;
	private GameObject parent;
	private boolean visible = false;
	
	public Collider(GameObject parent)
	{
		this.parent = parent;
		collider = new Rectangle2D.Double(parent.getShape().getPosition().getX(), 
				parent.getShape().getPosition().getY(), 
				parent.getShape().getSize().getWidth(), 
				parent.getShape().getSize().getHeight());
	}
	
	
	public Rectangle2D getBounds()
	{
		return collider;
	}
	
	
	//move the caller away from the target
	public void solveCollision(Collider c)
	{
		Rectangle2D r = collider.createIntersection(((Collider)c).getBounds());
		
		int dx = 0, dy = 0;
		
		if(r.getWidth() < r.getHeight())
		{
			if(r.getX() > collider.getX() + collider.getWidth()/2)
				dx = -1;
			else
				dx = 1;
		}
		else
		{
			if(r.getY() > collider.getY() + collider.getHeight()/2)
				dy = -1;
			else
				dy = 1;
		}

		parent.getShape().translate(dx * r.getWidth(), dy * r.getHeight());
		updateComponent();
	}
	
	
	//detect and solve collision
	public void collideWith(GameObject o)
	{
		boolean solveRequest = false;
		ArrayList<IGameObjectComponent> colliders = o.getComponentList(Collider.class);
		
		if(colliders == null)
			return;
		
		for(int i = 0; i < colliders.size(); i++)
		{
			if(colliders.get(i) != null)
			{
				if(!collider.equals(((Collider)colliders.get(i)).getBounds()) && collider.intersects(((Collider)colliders.get(i)).getBounds()))
				{
					//Collision confirmed	
					ArrayList<IGameObjectComponent> handlers = parent.getComponentList(ICollisionHandler.class);
					solveRequest = false;
					if(handlers != null)
					{
						for(int k = 0; k < handlers.size(); k++)
						{
							if(handlers.get(k) != null)
							{
								solveRequest |= ((ICollisionHandler)handlers.get(k)).handleCollisionWith(o);
							}
						}
						if(solveRequest)
							solveCollision((Collider)colliders.get(i));
					}
					else
						solveCollision((Collider)colliders.get(i));
				}
			}
		}
	}
	
	
	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	@Override
	public void updateComponent()
	{
		collider.setRect(parent.getPosition().getX(),
				parent.getPosition().getY(),
				parent.getShape().getSize().getWidth(),
				parent.getShape().getSize().getHeight());
	}


	@Override
	public void drawComponent(Graphics g)
	{
		if(visible)
		{
			g.setColor(Color.red);
			g.drawRect((int)collider.getX(), (int)collider.getY(), (int)collider.getWidth(), (int)collider.getHeight());
		}
	}
}
