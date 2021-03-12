import java.awt.*;

import javax.swing.*;

public class Body extends JPanel
{
	public Point position;
	public Dimension size;
	public Shape shape;
	
	
	Body(Point position, Dimension size)
	{
		this.position = position;
		this.size = size;
	}
	
	Body(Shape shape)
	{
		this.shape=shape;
	}
	
	public void paint(Graphics2D g)
	{
		g.setColor(Color.black);
		g.draw(shape);
	}
	
	

}
