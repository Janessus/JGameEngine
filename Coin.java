import java.awt.Point;

@SuppressWarnings("serial")
public class Coin extends Collectable
{
	Coin(Game game, Point pos, int value)
	{
		super(game, new CoinShape(pos), Collectables.GOLD);
		this.value = value;
		addComponent(new Collider(this));
		addComponent(new CollectableCollisionHandler(this, type, value));
	}
	
	@Override
	public boolean handleCollisionWith(GameObject o)
	{
		return true;
	}
}
