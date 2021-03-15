import java.awt.Point;

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
