
@SuppressWarnings("serial")
public abstract class Collectable extends GameObject implements ICollisionHandler
{	
	Collectables type;
	int value = 0;
	
	Collectable(Game game, MyShape shape, Collectables type)
	{
		super(game, shape);
		this.type = type;
	}	
}
