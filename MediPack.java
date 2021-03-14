
@SuppressWarnings("serial")
public class MediPack extends CollectableComponent
{
	public MediPack(GameObject parent, Collectables type, int value)
	{
		super(parent, type, value);
	}

	@Override
	public boolean handleCollisionWith(GameObject o)
	{
		return true;
	}
}
