import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

public class Camera extends JPanel
{
	int objectCount = 0;
	boolean following = false;
	
	Point offset;
	Point globalOffset;
	Point position;
	GameObject target;
	Game game;
	
	
	public Camera(Game game)
	{
		this.game = game;
		position = new Point(0, 0);
		offset = new Point(0, 0);
		globalOffset = new Point(0, 0);
	}
	
	
	public void follow(GameObject o)
	{
		following = true;
		target = o;
	}
	
	
	//Prepare the next frame
	void renderCamera()
	{
		//following 
		if(following)
		{
			globalOffset.x = (int) ((game.getRootPane().getSize().width / 2) - target.getPosition().getX() - target.shape.getSize().width/2);
			globalOffset.y = (int) ((game.getRootPane().getSize().height / 2) - target.getPosition().getY() - target.shape.getSize().height/2);
		}
		
		//calculate mouse induced camera movement
		if(game.mousePos != null)
		{
			offset.x = game.mousePos.x - (game.getRootPane().getSize().width / 2);
			offset.y = game.mousePos.y - (game.getRootPane().getSize().height / 2);
			
			globalOffset.x -= offset.x;
			globalOffset.y -= offset.y;
		}
		
		for(int i = 0; i < game.gameObjects.size(); i++)
		{
			game.gameObjects.get(i).shape.translate(globalOffset.x, globalOffset.y);
		}
	}
	
	
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK);
		renderCamera();
		
		for(int i = 0; i < game.gameObjects.size(); i++)
		{
			game.gameObjects.get(i).paint(g);
		}
	}
}
