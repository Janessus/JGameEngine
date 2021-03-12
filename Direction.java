
public class Direction
{
	public double x = 0;
	public double y = 0;
	
	public Direction(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getCombinedLength()
	{
		return Math.sqrt(x * x + y * y);
	}
	
	public Direction normalize()
	{
		return normalize(this.x, this.y);
	}
	
	public Direction normalize(double x, double y)
	{
		if(Math.abs(x) > Math.abs(y))
		{
			y = y/Math.abs(x);
			x = 1;
		}
		else if(Math.abs(y) > Math.abs(x))
		{
			x = x/Math.abs(y);
			y = 1;
		}
		else if(x == y && x != 0)
		{
			x = x/Math.abs(x);
			y = y/Math.abs(y);
		}
		return new Direction(x, y);
	}
	
	public String toString()
	{
		return "x=" + this.x + ", y=" + this.y;
	}
}
