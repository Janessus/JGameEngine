import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

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
		g.fillOval(getPosition().x, getPosition().y, getSize().width, getSize().height);
		g.setColor(Color.BLACK);
		g.drawOval(getPosition().x, getPosition().y, getSize().width, getSize().height);
		g.setFont(new Font("monospace", Font.BOLD, 15));
		g.drawString("$", getPosition().x + 12, getPosition().y + 21);
	}

}
