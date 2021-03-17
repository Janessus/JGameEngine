package userSpace.items.collectable.adrenalin;
import java.awt.Point;

import core.Game;
import gameObject.GameObject;
import gameObject.components.CollectableComponent;
import gameObject.components.Collider;
import templates.Collectables;

public class Adrenalin extends GameObject
{
	public Adrenalin(Game game, Point pos, int value)
	{
		super(game, new AdrenalinShape(pos));
		addComponent(new Collider(this));
		addComponent(new CollectableComponent(this, Collectables.STAMINA, value));
	}
}
