package userSpace.items.collectable.armorPlate;

import java.awt.Point;

import core.Game;
import gameObject.GameObject;
import gameObject.components.collectable.CollectableComponent;
import gameObject.components.collectable.Collectables;
import gameObject.components.collider.Collider;

public class ArmorPlate extends GameObject
{
	public ArmorPlate(Game game, Point pos, int value)
	{
		super(game, new ArmorPlateShape(pos, 30, 30));
		addComponent(new Collider(this));
		addComponent(new CollectableComponent(this, Collectables.ARMOR, value));
	}
}
