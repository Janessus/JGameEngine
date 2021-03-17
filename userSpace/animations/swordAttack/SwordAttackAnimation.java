package userSpace.animations.swordAttack;

import java.awt.Graphics;
import java.util.List;

import templates.Animation;
import templates.IAnimationFrame;

public class SwordAttackAnimation extends Animation
{

	public SwordAttackAnimation(int milliDuration, List<IAnimationFrame> animationFrames)
	{
		super(milliDuration, animationFrames);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void setupFrames()
	{
		animationFrames.add(new IAnimationFrame() {
			@Override
			public void paint(Graphics g)
			{
//				g.fillArc(duration, duration, duration, duration, duration, duration);
			}
		});
	}

}
