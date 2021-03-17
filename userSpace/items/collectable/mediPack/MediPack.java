package userSpace.items.collectable.mediPack;
import java.awt.Point;

import core.Game;
import gameObject.GameObject;
import gameObject.components.collectable.CollectableComponent;
import gameObject.components.collectable.Collectables;
import gameObject.components.collider.Collider;

@SuppressWarnings("serial")
public class MediPack extends GameObject
{
	public MediPack(Game game, Point pos, int value)
	{
		super(game, new MediPackShape(pos));
		addComponent(new Collider(this));
		addComponent(new CollectableComponent(this, Collectables.HEALTH, value));
	}
}
