package core;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import gameObject.components.collider.Collider;
import userSpace.Camera;
import userSpace.items.collectable.adrenalin.Adrenalin;
import userSpace.items.collectable.coin.Coin;
import userSpace.items.collectable.mediPack.MediPack;
import userSpace.items.solid.walls.BasicWall;
import userSpace.players.artificialPlayer.ArtificialPlayer;
import userSpace.players.humanPlayer.Player;

@SuppressWarnings("serial")
public class Game extends JPanel
{
	private boolean run = false;
	private int fps;

	private JFrame window;
	private Camera camera;
	private ArrayList<GameObject> gameObjects;
	private ArrayList<GameObject> collisionSubscribers;
	private Point mousePos;
	private Player player;
	
	
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

		getGameObjects().add(new BasicWall(this, 0, 400, 600, 100));
		getGameObjects().add(new BasicWall(this, 500, 99, 80,80)); 
		
		for(int i = 0; i < 5; i++)
		{
			getGameObjects().add(new Coin		(this, new Point((int)(r.nextDouble() * getRootPane().getSize().width),((int) (r.nextDouble() * getRootPane().getSize().height))), 10));
			getGameObjects().add(new MediPack	(this, new Point((int)(r.nextDouble() * getRootPane().getSize().width),((int) (r.nextDouble() * getRootPane().getSize().height))), 50));
			getGameObjects().add(new Adrenalin	(this, new Point((int)(r.nextDouble() * getRootPane().getSize().width),((int) (r.nextDouble() * getRootPane().getSize().height))), 50));
		}
		
		getGameObjects().add(new ArtificialPlayer(this));
		getGameObjects().add(player);
		
		//Set all colliders to visible
		for(int i = 0; i < getGameObjects().size(); i++)
		{
			ArrayList<GameObjectComponent> components = getGameObjects().get(i).getComponentList(Collider.class);
			
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
		getWindow().setContentPane(camera);
		getWindow().addKeyListener(new KeyListener() 
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
		return getFps();
	}
	
	
	private void processCollisionsFor(GameObject g)
	{
		ArrayList<GameObjectComponent> playerComponents = g.getComponentList(Collider.class);
		
		if(playerComponents != null)
		{
//			for(IGameObjectComponent playerCollider : playerComponents)
			for(int k = 0; k < playerComponents.size(); k++)
			{
//				for(GameObject go : gameObjects)
				for(int i = 0; i < getGameObjects().size(); i++)
				{
					if(!getGameObjects().get(i).equals(g))
					{
						((Collider)playerComponents.get(k)).collideWith((GameObject)getGameObjects().get(i));
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
		mousePos = getWindow().getMousePosition();
		for(int i = 0; i < getGameObjects().size(); i++)
			getGameObjects().get(i).updateObject();
		
		handleCollisions();
	}
	
	
	public void paint(Graphics g)
	{
		g.clearRect(0, 0, getWidth() , getHeight());
		this.camera.paint(g);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("monospace", Font.PLAIN, 12));
		g.drawString(getFps() + "fps", 0, 15);
		
		if(getMousePos() == null)
			g.drawString("Mouse=OUT_OF_FOCUS", 0, 30);
		else
			g.drawString("Mouse=" + getMousePos().x + ", " + getMousePos().y, 0, 30);
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


	public int getFps()
	{
		return fps;
	}


	public Point getMousePos()
	{
		return mousePos;
	}


	public JFrame getWindow()
	{
		return window;
	}


	public ArrayList<GameObject> getGameObjects()
	{
		return gameObjects;
	}
}
