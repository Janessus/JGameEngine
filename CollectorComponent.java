import java.awt.Graphics;
import java.util.List;

public class CollectorComponent implements IGameObjectComponent, ICollisionHandler
{
	private int gold;
	private int health;
	private int stamina;
	private int xp;
	
	GameObject parent;
	
	public CollectorComponent(GameObject parent)
	{
		this.parent = parent;
		gold = 0;
		health = 0;
		stamina = 0;
		xp = 0;
	}
	
	public String getAttributes()
	{
		return "Gold=" + gold + ", Health=" + health + ", Stamina=" + stamina + ", XP=" + xp;
	}
	
	//returns true if the item was collectet
	public boolean collect(Collectables type, int value)
	{
		switch (type) {
		case GOLD:
			gold += value;
			return true;
		case HEALTH:
			health += value;
			return true;
		case XP:
			xp += value;
			return true;
		case STAMINA:
			stamina += value;
			return true;
		}
		return false;
	}

	@Override
	public void updateComponent() {}

	@Override
	public boolean handleCollisionWith(GameObject o) {
		boolean returnVal = false;
		List<IGameObjectComponent> list = o.getComponentList(CollectableComponent.class);
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
