package gameObject.components.collectable;
import java.awt.Graphics;
import java.util.ArrayList;

import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import gameObject.components.ICollisionHandler;
import templates.GameShape;

public class CollectableComponent extends GameObjectComponent implements ICollisionHandler
{	
	private Collectables type;
	private int value = 0;
	
	
	GameObject parentObject;
	
	
	CollectableComponent(GameObject parent, Collectables type)
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
		ArrayList<GameObjectComponent> components = o.getComponentList(CollectorComponent.class);
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
