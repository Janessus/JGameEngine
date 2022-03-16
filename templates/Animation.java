package templates;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import core.Game;


public abstract class Animation
{
	private Point position;
	private double rotation;
	private int duration; //in ms
	private long elapsedTime; //in ns
	private int frameCounter;
	private int frames;
	private long maxFrameTime;
	
	protected List<IAnimationFrame> animationFrames;
	
	public abstract void setupFrames();
	
	
	public Animation(int milliDuration, List<IAnimationFrame> animationFrames)
	{
		this.duration = milliDuration;
		this.animationFrames = animationFrames;
		frames = animationFrames.size();
		elapsedTime = 0;
		frameCounter = 0;
		maxFrameTime = (long) ((frames * 1_000_000) / duration); //in ns
		
	}
	
	
	private void update()
	{
		elapsedTime += 1_000_000_000 / Game.getFPS();
		
		if(elapsedTime >= maxFrameTime)
		{
			frameCounter++;
			elapsedTime = 0;
		}
	}
	
	
	public void paint(Graphics g)
	{		
		update();
		animationFrames.get(frameCounter).paint(this, g);
	}


	public Point getPosition()
	{
		return position;
	}


	public void setPosition(Point position)
	{
		this.position = position;
	}


	public double getRotation()
	{
		return rotation;
	}


	public void setRotation(double rotation)
	{
		this.rotation = rotation;
	}
}
