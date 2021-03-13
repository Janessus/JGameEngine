import java.awt.Rectangle;
import java.util.ArrayList;

public class Collider implements IGameObjectComponent
{
	private Rectangle collider;
	private GameObject parent;
	private boolean solveRequest = false;
	
	public Collider(GameObject parent)
	{
		this.parent = parent;
		collider = new Rectangle(parent.shape.getPosition().x, parent.shape.getPosition().y, (int)parent.shape.getSize().width, (int)parent.shape.getSize().height);
	}
	
	
	public Rectangle getBounds()
	{
		return collider;
	}
	
	
	//move the caller away from the target
	public void solveCollision(Collider c)
	{
		Rectangle r = collider.intersection(((Collider)c).getBounds());
		
		int dx = 0, dy = 0;
		
		if(r.width < r.height)
		{
			if(r.x > collider.x + collider.width/2)
				dx = -1;
			else
				dx = 1;
		}
		else
		{
			if(r.y > collider.y + collider.height/2)
				dy = -1;
			else
				dy = 1;
		}

		parent.shape.translate(dx * r.width, dy * r.height);
		updateComponent();
	}
	
	
	//detect and solve collision
	public void collideWith(GameObject o)
	{
		ArrayList<IGameObjectComponent> colliders = o.getComponentList(Collider.class);
		
		if(colliders == null)
			return;
		
		for(IGameObjectComponent c : colliders)
		{
			if(c != null)
			{
				if(!collider.equals(((Collider)c).getBounds()) && collider.intersects(((Collider)c).getBounds()))
				{
					//Collision confirmed	
//					System.out.println("requesting interface, type=" + o.getClass());
					ArrayList<IGameObjectComponent> handlers = parent.getComponentList(ICollisionHandler.class);
//					System.out.println(handlers);
					solveRequest = false;
					if(handlers != null)
					{
						for(IGameObjectComponent ch : handlers)
						{
							if(ch != null)
							{
								solveRequest |= ((ICollisionHandler)ch).handleCollisionWith(o);
							}
						}
						if(solveRequest)
							solveCollision((Collider)c);
					}
					else
						solveCollision((Collider)c);
				}
			}
		}
	}
	
	
	@Override
	public void updateComponent()
	{
		collider.x = parent.getPosition().x;
		collider.y = parent.getPosition().y;
		collider.width = parent.shape.getSize().width;
		collider.height = parent.shape.getSize().height;
	}
}
