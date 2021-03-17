import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import gameObject.components.GameObjectComponent;
import templates.Direction;
import userSpace.players.Player;

public class PlayerPhysicsController extends GameObjectComponent
{
	
	private JFrame window;
	private Player player;
	private Physics p;
	public final double acceleration = 0.001;
	
	
	public PlayerPhysicsController(Player parent, JFrame window, Physics p)
	{
		this.p = p;
		this.player = parent;
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
					//player.setMoving(Movements.UP, false);
					break;
				case KeyEvent.VK_A:
					//player.setMoving(Movements.LEFT, false);
					break;
				case KeyEvent.VK_S:
					//player.setMoving(Movements.DOWN, false);
					break;
				case KeyEvent.VK_D:
					//player.setMoving(Movements.RIGHT, false);
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
					//player.setMoving(Movements.UP, true);
					p.accelerate(acceleration, new Direction(0, -1));
					break;
				case KeyEvent.VK_A:
					//player.setMoving(Movements.LEFT, true);
					p.accelerate(acceleration, new Direction(-1, 0));
					break;
				case KeyEvent.VK_S:
					//player.setMoving(Movements.DOWN, true);
					p.accelerate(acceleration, new Direction(0, 1));
					break;
				case KeyEvent.VK_D:
					//player.setMoving(Movements.RIGHT, true);
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
