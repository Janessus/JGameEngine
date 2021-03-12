import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

public class Game extends JPanel
{
	boolean run = false;
	
	int fps;

	JFrame window;
	Camera camera;
	ArrayList<GameObject> gameObjects;
	Point mousePos;
	Player player;
	Movements moves;

	
	Game(JFrame window)
	{
		super();
		this.window = window;
		init();
	}
	
	public void run()
	{
		run = true;
		long prev = System.nanoTime();
		
		while(run)
		{
			long currentTime = System.nanoTime();
			
			
			//actual work
			updateGame();
			repaint();
			
			
			
			//fps
			if(currentTime - prev == 0)
				--prev;
			
			fps = (int)((1000.0 * 1000000) / (currentTime - prev));
			prev = currentTime;
		}
	}
	
	
	void init()
	{
		GameObject gObj1 = new GameObject(new RectShape(0, 400, 600, 100));
		gObj1.addComponent(new Collider(gObj1));
		
		GameObject gObj2 = new GameObject(new RectShape(500, 99, 80,80));
		gObj2.addComponent(new Collider(gObj2));
		
		player = new Player("Player1", this);

		gameObjects = new ArrayList<GameObject>();
		gameObjects.add(gObj1);
		gameObjects.add(gObj2); 
		gameObjects.add(player);
		
		camera = new Camera(this, gameObjects);
		camera.follow(player);
		
		window.setContentPane(camera);
		window.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) 
			{
				int keyCode = e.getKeyCode();
				
				switch (keyCode) 
				{
					case KeyEvent.VK_ESCAPE:
						run = false;
						break;
				}
			}
		});
	}

	
	public int getFPS()
	{
		return fps;
	}
	
	
	public void handleCollisions()
	{
		ArrayList<IGameObjectComponent> playerComponents = player.getComponents(Collider.class);
		
		if(playerComponents != null)
		{
			for(IGameObjectComponent playerCollider : playerComponents)
			{
				for(GameObject go : gameObjects)
				{
					if(!go.equals(player))
					{
						((Collider)playerCollider).collidesWith((GameObject)go);
					}
				}
			}
		}
	}
	
	
	void updateGame()
	{
		fps = getFPS();
		mousePos = window.getMousePosition();
		for(GameObject go : gameObjects)
			go.updateObject();
		
		handleCollisions();
	}
	
	
	public void paint(Graphics g)
	{
		g.clearRect(0, 0, getWidth() , getHeight());
		this.camera.paint(g);
		
		g.setColor(Color.BLACK);
		g.drawString(fps + "fps", 0, 15);
		
		if(mousePos == null)
			g.drawString("Mouse=OUT_OF_FOCUS", 0, 30);
		else
			g.drawString("Mouse=" + mousePos.x + ", " + mousePos.y, 0, 30);
	}
	
	
	
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Game");
		Game game = new Game(window);
		
		window.add(game);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(game);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		window.setUndecorated(true);
		window.setVisible(true);
		
		game.run();
		window.dispose();
	}
}
