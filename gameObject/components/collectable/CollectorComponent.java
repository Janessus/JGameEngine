package gameObject.components.collectable;
import java.awt.Graphics;
import java.util.List;

import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import gameObject.components.ICollisionHandler;
import gameObject.components.attributeComponent.HealthAttributeComponent;
import gameObject.components.attributeComponent.StaminaAttributeComponent;

public class CollectorComponent extends GameObjectComponent implements ICollisionHandler
{
	private GameObject parent;
	
	public CollectorComponent(GameObject parent)
	{
		this.parent = parent;
	}
	
	//returns true if the item was collectet
	public boolean collect(Collectables type, int value)
	{
		switch (type) {
		case GOLD:
			return true;
			
		case HEALTH:
			HealthAttributeComponent health = (HealthAttributeComponent) parent.getFirstComponent(HealthAttributeComponent.class);
			if(health == null)
				return false;
			return health.add(value);
			
		case XP:
			return true;
			
		case STAMINA:
			StaminaAttributeComponent stamina = (StaminaAttributeComponent) parent.getFirstComponent(StaminaAttributeComponent.class);
			if(stamina == null)
				return false;
			return stamina.add(value);
		}
		return false;
	}

	@Override
	public void updateComponent() {}

	@Override
	public boolean handleCollisionWith(GameObject o) {
		boolean returnVal = false;
		List<GameObjectComponent> list = o.getComponentList(CollectableComponent.class);
		if(list != null)
		{
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i) == null)
					returnVal = true;
				else
					returnVal |= ((CollectableComponent)list.get(i)).handleCollisionWith(parent);
			}
			return returnVal;
		}
		else
			return true;
	}

	@Override
	public void drawComponent(Graphics g) {}

	@Override
	public void setVisible(boolean visible)
	{
		// TODO Auto-generated method stub
		
	}
}
