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
	
	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getResultingLength()
	{
		return Math.sqrt(x * x + y * y);
	}
	
	
	public static Direction getDirection(Point2D from, Point2D to)
	{
		return new Direction(to.getX() - from.getX(), to.getY() - from.getY()).normalize();
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
	
	public String toString()
	{
		return "x=" + this.x + ", y=" + this.y;
	}
}
