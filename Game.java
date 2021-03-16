import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.*;

public class Game extends JPanel
{
	boolean run = false;
	
	int fps;

	JFrame window;
	Camera camera;
	ArrayList<GameObject> gameObjects, collisionSubscribers;
	Point mousePos;
	Player player;
	Movements moves;

	
	Game(JFrame window)
	{
		super();
		this.window = window;
		gameObjects = new ArrayList<GameObject>();
		collisionSubscribers = new ArrayList<GameObject>();
		init();
	}
	
	private void run()
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
	
	
	//Create initial GameObjects here
	private void setupObjects()
	{
		Random r = new Random();
		player = new Player(this);

		gameObjects.add(new BasicWall(this, 0, 400, 600, 100));
		gameObjects.add(new BasicWall(this, 500, 99, 80,80)); 
		
		for(int i = 0; i < 5; i++)
		{
			gameObjects.add(new Coin		(this, new Point((int)(r.nextDouble() * getRootPane().getSize().width),((int) (r.nextDouble() * getRootPane().getSize().height))), 10));
			gameObjects.add(new MediPack	(this, new Point((int)(r.nextDouble() * getRootPane().getSize().width),((int) (r.nextDouble() * getRootPane().getSize().height))), 50));
			gameObjects.add(new Adrenalin	(this, new Point((int)(r.nextDouble() * getRootPane().getSize().width),((int) (r.nextDouble() * getRootPane().getSize().height))), 50));
		}
		
		gameObjects.add(new ArtificialPlayer(this));
		gameObjects.add(player);
		
		//Set all colliders to visible
		for(int i = 0; i < gameObjects.size(); i++)
		{
			ArrayList<IGameObjectComponent> components = gameObjects.get(i).getComponentList(Collider.class);
			
			if(components != null)
			{
				for(int k = 0; k < components.size(); k++)
				{
					components.get(k).setVisible(true);
				}
			}
		}
		camera.follow(player);
	}
	
	private void init()
	{
		camera = new Camera(this);
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
	
	private void processCollisionsFor(GameObject g)
	{
		ArrayList<IGameObjectComponent> playerComponents = g.getComponentList(Collider.class);
		
		if(playerComponents != null)
		{
//			for(IGameObjectComponent playerCollider : playerComponents)
			for(int k = 0; k < playerComponents.size(); k++)
			{
//				for(GameObject go : gameObjects)
				for(int i = 0; i < gameObjects.size(); i++)
				{
					if(!gameObjects.get(i).equals(g))
					{
						((Collider)playerComponents.get(k)).collideWith((GameObject)gameObjects.get(i));
					}
				}
			}
		}
	}
	
	public void subscribeCollisions(GameObject o)
	{
		collisionSubscribers.add(o);
	}
	
	private void handleCollisions()
	{
		for(int i = 0; i < collisionSubscribers.size(); i++)
			processCollisionsFor(collisionSubscribers.get(i));
	}
	
	
	private void updateGame()
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
		g.setFont(new Font("monospace", Font.PLAIN, 12));
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
		
		game.setupObjects();
		game.run();
		window.dispose();
	}
}
