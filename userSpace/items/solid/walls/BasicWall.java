package userSpace.items.solid.walls;
import core.Game;
import gameObject.GameObject;
import gameObject.components.Collider;

@SuppressWarnings("serial")
public class BasicWall extends GameObject
{
	public BasicWall(Game game, int x, int y, int w, int h)
	{
		super(game, new RectShape(x, y, w, h));
		addComponent(new Collider(this));
	}
}
