package userSpace;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import templates.GameShape;

public class CoinShape extends GameShape
{

	public CoinShape(Point pos)
	{
		super(pos.x, pos.y, 30, 30);
	}

	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillOval((int)getPosition().getX(), (int)getPosition().getY(), getSize().width, getSize().height);
		g.setColor(Color.BLACK);
		g.drawOval((int)getPosition().getX(), (int)getPosition().getY(), getSize().width, getSize().height);
		g.setFont(new Font("monospace", Font.BOLD, 15));
		g.drawString("$", (int)getPosition().getX() + 12, (int)getPosition().getY() + 21);
	}

}
