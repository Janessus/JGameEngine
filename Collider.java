import java.awt.Rectangle;
import java.util.ArrayList;

public class Collider implements IGameObjectComponent
{
	private Rectangle collider;
	private GameObject parent;
	
	
	public Collider(GameObject parent)
	{
		this.parent = parent;
		collider = new Rectangle();

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
				if(collider.intersects(((Collider)c).getBounds()))
				{
					Rectangle r = collider.intersection(((Collider)c).getBounds());
					
					int dx = 0, dy = 0;
					
					if(r.width < r.height)
					{
						if(r.x > parent.shape.getCenter().x)
							dx = -1;
						else
							dx = 1;
					}
					else
					{
						if(r.y > parent.shape.getCenter().y)
							dy = -1;
						else
							dy = 1;
					}
					parent.shape.translate(dx * r.width, dy * r.height);
					update();
				}
			}
		}
	}
	

	@Override
	public void update()
	{
		collider.x = parent.getPosition().x;
		collider.y = parent.getPosition().y;
		collider.width = parent.getSize().width;
		collider.height = parent.getSize().height;		
	}

}
