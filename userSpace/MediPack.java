package userSpace;
import java.awt.Point;

import core.Game;
import gameObject.GameObject;
import gameObject.components.CollectableComponent;
import gameObject.components.Collider;
import templates.Collectables;

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
