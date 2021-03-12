import java.awt.Rectangle;
import java.util.ArrayList;

public class Collider implements IGameObjectComponent
{
	private Rectangle collider;
	private GameObject parent;	
	
	public Collider(GameObject parent)
	{
		this.parent = parent;
		collider = new Rectangle(parent.shape.getPosition().x, parent.shape.getPosition().y, (int)parent.shape.getSize().width, (int)parent.shape.getSize().height);
	}
	
	
	public Rectangle getBounds()
	{
		return collider;
	}
	
	
	public void collidesWith(GameObject o)
	{
		ArrayList<IGameObjectComponent> colliders = o.getComponents(Collider.class);
		
		if(colliders == null)
			return;
		
		for(IGameObjectComponent c : colliders)
		{
			if(c != null)
			{
				//System.out.println(collider.toString() + ", " + ((Collider)c).getBounds().toString());
				if(!collider.equals(((Collider)c).getBounds()) && collider.intersects(((Collider)c).getBounds()))
				{
					//System.out.println("Collision ");
					
					Rectangle r = collider.intersection(((Collider)c).getBounds());
										
					int dx = 0, dy = 0;
					
					if(r.width < r.height)
					{
//						System.out.println(1);
						if(r.x > collider.x + collider.width/2)
							dx = -1;
						else
							dx = 1;
					}
					else
					{
//						System.out.println(2);
						if(r.y > collider.y + collider.height/2)
							dy = -1;
						else
							dy = 1;
					}
					
//					System.out.println("Intersection: " + r.toString());
//					System.out.println("Moving: " + dx * r.width + ", " + dy * r.height);
					parent.shape.translate(dx * r.width, dy * r.height);
					updateComponent();
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
