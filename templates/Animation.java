package templates;

import java.awt.Graphics;
import java.util.List;
import core.Game;


public abstract class Animation
{
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
		elapsedTime = 0;
		frameCounter = 0;
		maxFrameTime = (long) ((frames * 1_000_000) / duration); //in ns
		frames = animationFrames.size();
	}
	
	
	private void update()
	{
		elapsedTime += 1_000_000_000 / Game.getFPS();
		
		if(elapsedTime >= maxFrameTime)
		{
			frameCounter++;
		}
	}
	
	
	public void paint(Graphics g)
	{		
		update();
		animationFrames.get(frameCounter).paint(g);
	}
}
