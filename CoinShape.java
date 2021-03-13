import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class CoinShape extends MyShape
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
		g.setColor(Color.orange);
		g.drawOval(getPosition().x, getPosition().y, getSize().width, getSize().height);
		g.setFont(new Font("Times new roman", Font.BOLD, 20));
		g.drawString("$", getPosition().x + 10, getPosition().y + 21);
		g.setFont(new Font("monospace", Font.PLAIN, 12));
	}

}
