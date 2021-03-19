package userSpace.items.collectable.adrenalin;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import templates.GameShape;

public class AdrenalinShape extends GameShape
{
	private BufferedImage texture;
	public AdrenalinShape(Point pos)
	{
		super(pos.x, pos.y, 60, 60);
		try {
			texture = ImageIO.read(new File("./src/resources/adrenalin.png").getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace(); //TODO
//			texture = textureNotFound();
		}
	}

	@Override
	public void paint(Graphics g)
	{
		g.drawImage(texture, (int)getPosition().getX() - 5, (int)getPosition().getY() - 5, 70, 70, null);
	}
}
