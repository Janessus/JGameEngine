package userSpace.items.collectable.armorPlate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import templates.GameShape;

public class ArmorPlateShape extends GameShape
{

	public ArmorPlateShape(Point pos, int w, int h)
	{
		super(pos.x, pos.y, w, h);
	}

	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect((int)getPosition().getX(), (int)getPosition().getY(), getSize().width, getSize().height);
	}

}
