import java.awt.Point;

public class Adrenalin extends GameObject
{
	Adrenalin(Game game, Point pos, int value)
	{
		super(game, new AdrenalinShape(pos));
		addComponent(new Collider(this));
		addComponent(new CollectableComponent(this, Collectables.STAMINA, value));
	}
}
