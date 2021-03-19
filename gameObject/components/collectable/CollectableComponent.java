package gameObject.components.collectable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import gameObject.components.ICollisionHandler;

public class CollectableComponent extends GameObjectComponent implements ICollisionHandler
{	
	private Collectables type;
	private int value = 0;
	
	
	CollectableComponent(GameObject parent, Collectables type)
	{
		super(parent);
		this.type = type;
	}
	
	
	public CollectableComponent(GameObject parent, Collectables type, int value)
	{
		super(parent);
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
					getParent().destroy();
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
