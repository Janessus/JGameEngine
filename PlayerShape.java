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

/*
	@Override
	public void updateCollider()
	{
		collider.x = getPosition().x - collider.width/2;
		collider.y = getPosition().y - collider.height/2;
		collider.width = getSize().width;
		collider.height = getSize().height;
	}
*/	
	@Override
	public void paint(Graphics g)
	{	
		g.setColor(Color.GREEN);
		g.fillOval(getCenter().x, getCenter().y, getSize().width, getSize().height);
/*		
		g.setColor(Color.RED);
		g.drawRect(collider.x, collider.y, collider.width, collider.height);
*/
	}
}
