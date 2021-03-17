package userSpace.items.solid.walls;
import java.awt.Color;
import java.awt.Graphics;
import templates.GameShape;

public class RectShape extends GameShape
{
	public RectShape(int x, int y, int w, int h)
	{
		super(x, y, w, h);
	}


	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect((int)getPosition().getX(), (int)getPosition().getY(), getSize().width, getSize().height);
/*
		g.setColor(Color.RED);
		g.drawRect(collider.x, collider.y, collider.width, collider.height);
*/
	}
}
