package core;
import java.awt.geom.Point2D;

public class Direction
{
	private double x;
	private double y;
	
	public Direction(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Direction()
	{
		x = 0;
		y = 0;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}
	
	
	public double getAngle(Direction d)
	{
		double dotProduct = (x * d.x) + (y * d.y);
		return Math.acos(dotProduct/(getResultingLength() * d.getResultingLength())) * (180/Math.PI); 
	}
	

	public double getResultingLength()
	{
		return Math.sqrt(x * x + y * y);
	}
	
	
	public static Direction getDirection(Point2D from, Point2D to)
	{
		if(from == null || to == null)
			return null;
		return new Direction(to.getX() - from.getX(), to.getY() - from.getY()).normalize();
	}
	
	public Direction getDirection(Point2D to)
	{
		if(to == null)
			return null;
		return new Direction(to.getX() - getX(), to.getY() - getY()).normalize();
	}

	
	public Direction normalize()
	{
		double l = getResultingLength();
		if(l != 0)
		{
			x /= l;
			y /= l;
		}
		return this;
	}
	
	public void setDirection(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "x=" + this.x + ", y=" + this.y;
	}
}
