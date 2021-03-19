package userSpace.items.collectable.adrenalin;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
			System.out.println("Width " + super.getSize().getWidth());
			texture = new BufferedImage(super.getSize().width, super.getSize().height, BufferedImage.TYPE_INT_ARGB);
			Graphics g = texture.createGraphics();
			g.setColor(Color.BLACK);
			g.setFont(new Font("monospace", Font.BOLD, 8));
			drawMultiLineString(g, "   TEXTURE\nNOT FOUND", 6, 15);
			g.dispose();
		}
	}
	
	
	void drawMultiLineString(Graphics g, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
	

	@Override
	public void paint(Graphics g)
	{
		g.drawImage(texture, (int)getPosition().getX() - 5, (int)getPosition().getY() - 5, 70, 70, null);
	}
}
