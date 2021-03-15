import java.awt.Graphics;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CollectableComponent implements IGameObjectComponent, ICollisionHandler
{	
	Collectables type;
	int value = 0;
	
	
	GameObject parentObject;
	
	
	CollectableComponent(GameObject parent, GameShape shape, Collectables type)
	{
		this.type = type;
		this.parentObject = parent;
	}
	
	
	public CollectableComponent(GameObject parent, Collectables type, int value)
	{
		this.parentObject = parent;
		this.type = type;
		this.value = value;
	}
	
	public void updateComponent() {}
	
	
	// returns true if the item was not destroyed, false if the object was collected and destroyed
	public boolean onCollision(GameObject o)
	{
		ArrayList<IGameObjectComponent> components = o.getComponentList(CollectorComponent.class);
		if(components != null)
		{
			for(int i = 0; i < components.size(); i++)
			{
				if(((CollectorComponent)components.get(i)).collect(type, value))
				{
					parentObject.destroy();
					return false;
				}
			}
		}
		return false;
	}

	
	@Override
	public boolean handleCollisionWith(GameObject o)
	{
		return onCollision(o);
	}

	
	public void drawComponent(Graphics g) {}


	@Override
	public void setVisible(boolean visible) {}

	
	
	
}
