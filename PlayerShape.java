import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

public class PlayerShape extends MyShape
{
	public PlayerShape()
	{
		super(700, 500, 50, 50);
	}

	
	@Override
	public void paint(Graphics g)
	{	
		g.setColor(Color.GREEN);
		g.fillOval(getPosition().x, getPosition().y, getSize().width, getSize().height);
	}
}
