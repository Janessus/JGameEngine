import java.util.Date;
import java.util.Vector;

public class Physics implements IGameObjectComponent
{
	private double mass;
	double friction = 1.001;
	GameObject parent;
	private Direction speed;
	Direction direction;
	Game game;
	double maxSpeed;
	
	public Physics(GameObject parent, Game game)
	{
		this.parent = parent;
		speed = new Direction(0, 0);
		mass = 1;
		this.game = game;
		direction = new Direction(0, 0);
		maxSpeed = 20;
	}
	
	public void doFriction()
	{
		return;
	}
	
	public void accelerate(double acceleration, Direction d)
	{
		//double force = acceleration * mass;
		// s = vt + 1/2at²
		
		System.out.println("Accelerate");
		if(speed.getCombinedLength() < maxSpeed)
		{	
			this.direction.x += d.x;
			this.direction.y += d.y;
			
			//this.direction.normalize(, acceleration);
			
			long dT = new Date().getTime();// - game.prevTime; TODO
			
			double dX = speed.x * direction.x * dT + 0.5 * acceleration * direction.x * dT * dT; 
			double dY = speed.y * direction.y * dT + 0.5 * acceleration * direction.y * dT * dT;
			
			System.out.println("Accelerate(" + dX + ", " + dY + ")" + ", dT=" + dT);
			
			if((int)dX > 0 || dY > 0)
				parent.shape.translate((int)dX, (int)dY);
			
			speed.x += acceleration * dT * direction.x;
			speed.y += acceleration * dT * direction.y;
		}
	}
	
	@Override
	public void updateComponent()
	{
		long dT = new Date().getTime();// - game.prevTime; TODO
		
		System.out.println("speed=" + speed.getCombinedLength());
		
		if(speed.getCombinedLength() != 0)
		{
			parent.shape.translate((int)(speed.x * dT * direction.x), (int)(speed.y * dT * direction.y));
			//speed.x /= friction;
			//speed.y /= friction;
		}
	}
}
