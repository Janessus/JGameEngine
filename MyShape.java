import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

public abstract class MyShape
{
	private Point position;	//absolute
	private Dimension size;
	private Point offset;	//relative to center
	private Point center;	//center of the object
	
	public abstract void paint(Graphics g);
	
	public MyShape(int x, int y, int w, int h)
	{
		position = new Point(x, y);
		size = new Dimension(w, h);
		offset = new Point(0, 0);
		center = new Point();
		updateCenter();
	}

	public void setOffset(Point p)
	{
		offset = p;
		updateCenter();
	}

	public void setSize(int w, int h)
	{
		size = new Dimension(w, h);
	}
	
	public Dimension getSize()
	{
		return size;
	}
	
	public void updateCenter()
	{
		center.setLocation(position.x - size.width/2 - offset.x, position.y - size.height/2 - offset.y);
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public Point getCenter()
	{
		return center;
	}

	public void setPosition(Point p)
	{
		position.x = p.x;
		position.y = p.y;
		updateCenter();
	}

	public void translate(int x, int y)
	{
		position.x += x;
		position.y += y;		
		updateCenter();
	}
	
	public String toString()
	{
		return "x=" + position.x + ", y=" + position.y + ", width=" + size.width + ", height=" + size.height;
	}
}
