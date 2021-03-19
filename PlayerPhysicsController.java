import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import core.Direction;
import gameObject.GameObject;
import gameObject.components.GameObjectComponent;

public class PlayerPhysicsController extends GameObjectComponent
{
	
	private JFrame window;
	private Physics p;
	public final double acceleration = 0.001;
	
	
	public PlayerPhysicsController(GameObject parent, JFrame window, Physics p)
	{
		super(parent);
		this.p = p;
		this.window = window;
		setupKeyListeners();
	}
	
	public void setupKeyListeners()
	{
		System.out.println("Setting up Listeners");
		window.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();

				switch (keyCode) {
				case KeyEvent.VK_W:
					//getParent().setMoving(Movements.UP, false);
					break;
				case KeyEvent.VK_A:
					//getParent().setMoving(Movements.LEFT, false);
					break;
				case KeyEvent.VK_S:
					//getParent().setMoving(Movements.DOWN, false);
					break;
				case KeyEvent.VK_D:
					//getParent().setMoving(Movements.RIGHT, false);
					break;

				default:
					break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				switch (keyCode) 
				{
				case KeyEvent.VK_W:
					//getParent().setMoving(Movements.UP, true);
					p.accelerate(acceleration, new Direction(0, -1));
					break;
				case KeyEvent.VK_A:
					//getParent().setMoving(Movements.LEFT, true);
					p.accelerate(acceleration, new Direction(-1, 0));
					break;
				case KeyEvent.VK_S:
					//getParent().setMoving(Movements.DOWN, true);
					p.accelerate(acceleration, new Direction(0, 1));
					break;
				case KeyEvent.VK_D:
					//getParent().setMoving(Movements.RIGHT, true);
					p.accelerate(acceleration, new Direction(1, 0));
					break;
				
				}
			}
		});
	}
	
	@Override
	public void updateComponent()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawComponent(Graphics g)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisible(boolean visible)
	{
		// TODO Auto-generated method stub
		
	}

}
