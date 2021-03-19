import java.awt.Graphics;
import java.awt.Graphics;
import java.util.Date;
import java.util.Vector;

import core.Direction;
import core.Game;
import gameObject.GameObject;
import gameObject.components.GameObjectComponent;

public class Physics extends GameObjectComponent
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
		super(parent);
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
		if(speed.getResultingLength() < maxSpeed)
		{	
//			this.direction.getX() += d.getX();
//			this.direction.getY() += d.getY();
			
			//this.direction.normalize(, acceleration);
			
			long dT = new Date().getTime();// - game.prevTime; TODO
			
			double dX = speed.getX() * direction.getX() * dT + 0.5 * acceleration * direction.getX() * dT * dT; 
			double dY = speed.getY() * direction.getY() * dT + 0.5 * acceleration * direction.getY() * dT * dT;
			
			System.out.println("Accelerate(" + dX + ", " + dY + ")" + ", dT=" + dT);
			
			if((int)dX > 0 || dY > 0)
				parent.getShape().translate((int)dX, (int)dY);
			
//			speed.getX() += acceleration * dT * direction.getX();
//			speed.getY() += acceleration * dT * direction.getY();
		}
	}
	
	@Override
	public void updateComponent()
	{
		long dT = new Date().getTime();// - game.prevTime; TODO
		
		System.out.println("speed=" + speed.getResultingLength());
		
		if(speed.getResultingLength() != 0)
		{
			parent.getShape().translate((int)(speed.getX() * dT * direction.getX()), (int)(speed.getY() * dT * direction.getY()));
			//speed.getX() /= friction;
			//speed.getY() /= friction;
		}
	}

	@Override
	public void drawComponent(Graphics g)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisible(boolean visible)
	{
		// TODO Auto-generated method stub
		
	}
}
