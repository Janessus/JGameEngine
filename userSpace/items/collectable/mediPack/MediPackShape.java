package userSpace.items.collectable.mediPack;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import templates.GameShape;

public class MediPackShape extends GameShape
{
	
	public MediPackShape(Point pos)
	{
		super(pos.x, pos.y, 50, 40);
	}

	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect((int)getPosition().getX(), (int)getPosition().getY(), getSize().width, getSize().height);

		g.setColor(Color.BLACK);
		g.drawRect((int)getPosition().getX(), (int)getPosition().getY(), getSize().width, getSize().height);
		
		g.setColor(Color.RED);
		g.setFont(new Font("monospace", Font.BOLD, 30));
		g.drawString("+", (int)getPosition().getX() + getSize().width/2 - 8, (int)getPosition().getY() + getSize().height -7);
		

	}

}
