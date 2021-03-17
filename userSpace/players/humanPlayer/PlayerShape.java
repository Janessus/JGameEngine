package userSpace.players.humanPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

import templates.GameShape;

public class PlayerShape extends GameShape
{
	public PlayerShape()
	{
		super(700, 500, 50, 50);
	}

	
	@Override
	public void paint(Graphics g)
	{	
		g.setColor(Color.GREEN);
		g.fillOval((int)getPosition().getX(), (int)getPosition().getY(), getSize().width, getSize().height);
	}
}
