import java.awt.Color;
import java.awt.Graphics;

public class ArtificialPlayerShape extends GameShape
{

	public ArtificialPlayerShape()
	{
		super(20, 20, 50, 50);
	}

	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.ORANGE);
		g.fillOval((int)getPosition().getX(), (int)getPosition().getY(), getSize().width, getSize().height);
	}

}
