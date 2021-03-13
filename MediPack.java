
@SuppressWarnings("serial")
public class MediPack extends Collectable
{

	MediPack(Game game, MyShape shape, Collectables type)
	{
		super(game, shape, type);
		
	}

	
	@Override
	public boolean handleCollisionWith(GameObject o)
	{
		return true;
	}
	
}
