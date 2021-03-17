package userSpace.items.collectable.coin;
import java.awt.Point;

import core.Game;
import gameObject.GameObject;
import gameObject.components.collectable.CollectableComponent;
import gameObject.components.collectable.Collectables;
import gameObject.components.collider.Collider;

public class Coin extends GameObject
{
	public Coin(Game game, Point pos, int value)
	{
		super(game, new CoinShape(pos));
		addComponent(new Collider(this));
		addComponent(new CollectableComponent(this, Collectables.GOLD, value));
	}
}
