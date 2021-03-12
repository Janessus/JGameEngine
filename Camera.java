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
	ArrayList<GameObject> objects;
	GameObject target;
	Game game;
	
	
	public Camera(Game game, ArrayList<GameObject> container)
	{
		this.game = game;
		position = new Point(0, 0);
		objects = new ArrayList<GameObject>();
		offset = new Point(0, 0);
		globalOffset = new Point(0, 0);

		for(GameObject go : container)
		{
			objects.add(go);
		}
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
			globalOffset.x = (game.getRootPane().getSize().width / 2) - target.getPosition().x - target.shape.getSize().width/2;
			globalOffset.y = (game.getRootPane().getSize().height / 2) - target.getPosition().y - target.shape.getSize().height/2;
		}
		
		//calculate mouse induced camera movement
		if(game.mousePos != null)
		{
			offset.x = game.mousePos.x - (game.getRootPane().getSize().width / 2);
			offset.y = game.mousePos.y - (game.getRootPane().getSize().height / 2);
			
			globalOffset.x -= offset.x;
			globalOffset.y -= offset.y;
		}
		
		for(GameObject go : objects)
		{
			go.shape.translate(globalOffset);
		}
	}
	
	
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK);
		renderCamera();
		
		g.drawString("Offset=" + offset.x + ", " + offset.y, 0, 45);
		g.drawString("Window=" + game.getRootPane().getSize().width + ", " + game.getRootPane().getSize().height, 0, 60);
		g.drawString("Target Position=" + target.shape.getPosition().x + ", " + target.shape.getPosition().y, 0, 75);
				
		for(GameObject go : objects)
		{
			go.paint(g);
		}
	}
}
