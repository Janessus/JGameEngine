import java.util.ArrayList;

@SuppressWarnings("serial")
public class CollectableCollisionHandler extends Collectable implements IGameObjectComponent, ICollisionHandler
{
	GameObject parentObject;
	
	public CollectableCollisionHandler(GameObject parent, Collectables type, int value)
	{
		super(parent.game, parent.shape, type);
		this.parentObject = parent;
		this.type = type;
		this.value = value;
	}
	
	@Override
	public void updateComponent() {}
	
	
	public boolean onCollision(GameObject o)
	{
		ArrayList<IGameObjectComponent> components = o.getComponentList(CollectorComponent.class);
		if(components != null)
		{
			for(int i = 0; i < components.size(); i++)
			{
				if(((CollectorComponent)components.get(i)).collect(((Collectable)parentObject).type, ((Collectable)parentObject).value))
				{
					parentObject.destroy();
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean handleCollisionWith(GameObject o)
	{
		return onCollision(o);
	}

}
