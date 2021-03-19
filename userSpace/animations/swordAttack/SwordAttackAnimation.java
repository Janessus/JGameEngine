package userSpace.animations.swordAttack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import templates.Animation;
import templates.IAnimationFrame;

public class SwordAttackAnimation extends Animation
{
	public SwordAttackAnimation(int milliDuration)
	{
		super(milliDuration, new ArrayList<IAnimationFrame>());
	}
	
	
	@Override
	public void setupFrames()
	{
		animationFrames.add(new IAnimationFrame() {
			@Override
			public void paint(Animation animation, Graphics g)
			{
				g.setColor(Color.BLACK);
				g.fillArc(getPosition().x, getPosition().y, 50, 20, 0, 180);
			}
		});
	}
}
