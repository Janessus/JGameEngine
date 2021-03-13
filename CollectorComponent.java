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
	
	public boolean collect(Collectables type, int value)
	{
		System.out.println("Collecting type=" + type + ", value=" + value);
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
		List<IGameObjectComponent> list = o.getComponentList(CollectableCollisionHandler.class);
		if(list != null)
		{
			for(IGameObjectComponent comp : list)
			{
				if(comp == null)
					return true;
				((CollectableCollisionHandler)comp).handleCollisionWith(parent);
			}
		}
		return true;
	}
}
