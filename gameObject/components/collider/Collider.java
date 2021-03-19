package gameObject.components.collider;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import gameObject.components.ICollisionHandler;

public class Collider extends GameObjectComponent
{
	private Rectangle2D collider;
	
	public Collider(GameObject parent)
	{
		super(parent);
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

		getParent().getShape().translate(dx * r.getWidth(), dy * r.getHeight());
		updateComponent();
	}
	
	
	//detect and solve collision
	public void collideWith(GameObject o)
	{
		boolean solveRequest = false;
		ArrayList<GameObjectComponent> colliders = o.getComponentList(Collider.class);
		
		if(colliders == null)
			return;
		
		for(int i = 0; i < colliders.size(); i++)
		{
			if(colliders.get(i) != null)
			{
				if(!collider.equals(((Collider)colliders.get(i)).getBounds()) && collider.intersects(((Collider)colliders.get(i)).getBounds()))
				{
					//Collision confirmed	
					ArrayList<GameObjectComponent> handlers = getParent().getComponentList(ICollisionHandler.class);
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
	public void updateComponent()
	{
		collider.setRect(getParent().getPosition().getX(),
				getParent().getPosition().getY(),
				getParent().getShape().getSize().getWidth(),
				getParent().getShape().getSize().getHeight());
	}


	@Override
	public void drawComponent(Graphics g)
	{
		if(isVisible())
		{
			g.setColor(Color.red);
			g.drawRect((int)collider.getX(), (int)collider.getY(), (int)collider.getWidth(), (int)collider.getHeight());
		}
	}
}
