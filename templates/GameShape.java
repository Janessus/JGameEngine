package templates;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public abstract class GameShape
{
	private Point2D position;	//absolute
	private Dimension size;
	public abstract void paint(Graphics g);
	
	public GameShape(int x, int y, int w, int h)
	{
		position = new Point2D.Double(x, y);
		size = new Dimension(w, h);
		new Point2D.Double(0, 0);
	}
	
	public double getCenterDistance(GameShape target)
	{
		return getCenter().distance(target.getCenter());
	}
	
	public double getCircleEdgeDistance(GameShape target)
	{
		return (getCenterDistance(target) - getRadius() - target.getRadius());
	}

	public double getRadius()
	{
		return Math.sqrt(size.getWidth() * size.getWidth() + size.getHeight() * size.getWidth())/2;
	}
	
	public void setOffset(Point2D p)
	{
	}

	public void setSize(int w, int h)
	{
		size = new Dimension(w, h);
	}
	
	public Dimension getSize()
	{
		return size;
	}

	public Point2D getPosition()
	{
		return position;
	}
	
	public Point2D getCenter()
	{
		return new Point2D.Double(position.getX() + size.width/2, position.getY() + size.height/2);
	}

	public void setPosition(Point2D p)
	{
		position.setLocation(p.getX(), p.getY());
	}

	public void translate(double x, double y)
	{
		position.setLocation(position.getX() + x, position.getY() + y);
	}
	
	public String toString()
	{
		return "x=" + position.getX() + ", y=" + position.getY() + ", width=" + size.width + ", height=" + size.height;
	}
}
