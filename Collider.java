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
		
		for(int i = 0; i < colliders.size(); i++)
		{
			if(colliders.get(i) != null)
			{
				if(!collider.equals(((Collider)colliders.get(i)).getBounds()) && collider.intersects(((Collider)colliders.get(i)).getBounds()))
				{
					//Collision confirmed	
//					System.out.println("requesting interface, type=" + o.getClass());
					ArrayList<IGameObjectComponent> handlers = parent.getComponentList(ICollisionHandler.class);
//					System.out.println(handlers);
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
		collider.x = parent.getPosition().x;
		collider.y = parent.getPosition().y;
		collider.width = parent.shape.getSize().width;
		collider.height = parent.shape.getSize().height;
	}
}
