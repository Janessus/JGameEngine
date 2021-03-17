package userSpace.items.collectable.coin;
import java.awt.Point;

import core.Game;
import gameObject.GameObject;
import gameObject.components.CollectableComponent;
import gameObject.components.Collider;
import templates.Collectables;

@SuppressWarnings("serial")
public class Coin extends GameObject
{
	public Coin(Game game, Point pos, int value)
	{
		super(game, new CoinShape(pos));
		addComponent(new Collider(this));
		addComponent(new CollectableComponent(this, Collectables.GOLD, value));
	}
}
