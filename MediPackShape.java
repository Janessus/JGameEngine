import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

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
		g.fillRect(getPosition().x, getPosition().y, getSize().width, getSize().height);

		g.setColor(Color.BLACK);
		g.drawRect(getPosition().x, getPosition().y, getSize().width, getSize().height);
		
		g.setColor(Color.RED);
		g.setFont(new Font("monospace", Font.BOLD, 30));
		g.drawString("+", getPosition().x + getSize().width/2 - 8, getPosition().y + getSize().height -7);
		

	}

}
