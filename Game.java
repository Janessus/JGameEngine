import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

public class Game extends JPanel
{
	public long prevTime;
	boolean run = false;
	
	int fps;

	JFrame window;
	Camera camera;
	Container gameObjects;
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
		
		while(run)
		{
			update();
			repaint();
		}
	}
	
	
	void init()
	{
		MyShape shape1 = new RectShape(0, 400, 600, 100);
		GameObject gObj1 = new GameObject(shape1);
		GameObject gObj2 = new GameObject(new RectShape(500, 99, 80,80));
		
		prevTime = new Date().getTime();
		
		player = new Player("Player1", this);
		
		gameObjects = new Container();
		
		gameObjects.add(gObj1);
		gameObjects.add(gObj2); 
		gameObjects.add(player);
		
		camera = new Camera(this, gameObjects);
		camera.follow(player);
		
		window.setContentPane(camera);
		
		window.addKeyListener(new KeyListener() {
		
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
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

	
	private int getFPS()
	{
		int fps;
		try {
			long currentTime = new Date().getTime();
			fps = (int)( 1000 / (currentTime - this.prevTime));
			this.prevTime = currentTime;
			
		} catch (Exception e) {
			fps = 0;
		}
		return fps;
	}
	
	
	public void handleCollisions()
	{
		ArrayList<IGameObjectComponent> components = player.getComponents(Collider.class);
		if(components != null)
		{
			for(IGameObjectComponent playerCollider : components)
			{
				for(int i = 0; i < gameObjects.getComponentCount(); i++)
				{
					if(!gameObjects.getComponent(i).equals(player))
					{
						((Collider)playerCollider).collidesWith((GameObject)gameObjects.getComponent(i));
					}
				}
			}
		}
	}
	
	
	void update()
	{
		fps = getFPS();
		mousePos = window.getMousePosition();
		for(int i = 0; i < gameObjects.getComponentCount(); i++)
			((GameObject) gameObjects.getComponent(i)).updateObject();
		
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
