package userSpace;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

import core.Game;
import gameObject.GameObject;

@SuppressWarnings("serial")
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
			globalOffset.x = (int) ((game.getRootPane().getSize().width / 2) - target.getPosition().getX() - target.getShape().getSize().width/2);
			globalOffset.y = (int) ((game.getRootPane().getSize().height / 2) - target.getPosition().getY() - target.getShape().getSize().height/2);
		}
		
		//calculate mouse induced camera movement
		if(game.getMousePos() != null)
		{
			offset.x = game.getMousePos().x - (game.getRootPane().getSize().width / 2);
			offset.y = game.getMousePos().y - (game.getRootPane().getSize().height / 2);
			
			globalOffset.x -= offset.x;
			globalOffset.y -= offset.y;
		}
		
		for(int i = 0; i < game.getGameObjects().size(); i++)
		{
			game.getGameObjects().get(i).getShape().translate(globalOffset.x, globalOffset.y);
		}
	}
	
	
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK);
		renderCamera();
		
		for(int i = 0; i < game.getGameObjects().size(); i++)
		{
			game.getGameObjects().get(i).paint(g);
		}
	}
}
