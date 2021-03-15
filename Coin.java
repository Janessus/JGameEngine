import java.awt.Point;

@SuppressWarnings("serial")
public class Coin extends GameObject
{
	Coin(Game game, Point pos, int value)
	{
		super(game, new CoinShape(pos));
		addComponent(new Collider(this));
		addComponent(new CollectableComponent(this, Collectables.GOLD, value));
	}
}
