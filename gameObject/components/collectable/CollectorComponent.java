package gameObject.components.collectable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import gameObject.components.ICollisionHandler;
import gameObject.components.attributeComponent.ArmorAttributeComponent;
import gameObject.components.attributeComponent.HealthAttributeComponent;
import gameObject.components.attributeComponent.StaminaAttributeComponent;
import templates.Armor;

public class CollectorComponent extends GameObjectComponent implements ICollisionHandler
{	
	public CollectorComponent(GameObject parent)
	{
		super(parent);
	}

	
	//returns true if the item was collectet
	public boolean collect(Collectables type, int value)
	{
		switch (type) {
		case GOLD:
			return true;
			
		case HEALTH:
			HealthAttributeComponent health = (HealthAttributeComponent) getParent().getFirstComponent(HealthAttributeComponent.class);
			if(health == null)
				return false;
			return health.add(value);
			
		case XP:
			return true;
			
		case ARMOR:
			ArmorAttributeComponent armor = (ArmorAttributeComponent) getParent().getFirstComponent(ArmorAttributeComponent.class);
			if(armor == null)
				return false;
			return armor.add(value);
			
		case STAMINA:
			StaminaAttributeComponent stamina = (StaminaAttributeComponent) getParent().getFirstComponent(StaminaAttributeComponent.class);
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
					returnVal |= ((CollectableComponent)list.get(i)).handleCollisionWith(getParent());
			}
			return returnVal;
		}
		else
			return true;
	}

	@Override
	public void drawComponent(Graphics g) {}

	@Override
	public void setVisible(boolean visible) {}
}
