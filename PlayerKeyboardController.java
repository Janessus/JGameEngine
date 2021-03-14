import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.JFrame;

public class PlayerKeyboardController implements IGameObjectComponent
{
	boolean up, down, left, right; //movement
	public double speed = 0.6;
	double distanceSum = 0;
	private JFrame window;
	private Player player;
	
	public PlayerKeyboardController(Player parent, JFrame window)
	{
		this.player = parent;
		this.window = window;
		up = false;
		down = false;
		left = false;
		right = false;
		setupKeyListeners();
	}
	
	public void setupKeyListeners()
	{
		window.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();

				switch (keyCode) {
				case KeyEvent.VK_W:
					setMoving(Movements.UP, false);
					break;
				case KeyEvent.VK_A:
					setMoving(Movements.LEFT, false);
					break;
				case KeyEvent.VK_S:
					setMoving(Movements.DOWN, false);
					break;
				case KeyEvent.VK_D:
					setMoving(Movements.RIGHT, false);
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
					setMoving(Movements.UP, true);
					break;
				case KeyEvent.VK_A:
					setMoving(Movements.LEFT, true);
					break;
				case KeyEvent.VK_S:
					setMoving(Movements.DOWN, true);
					break;
				case KeyEvent.VK_D:
					setMoving(Movements.RIGHT, true);
					break;
				
				}
			}
		});
	}
	
	public void setMoving(Movements move, boolean active)
	{
		switch (move) {
		case UP:
			up = active;
			break;
		case DOWN:
			down = active;
			break;
		case LEFT:
			left = active;
			break;
		case RIGHT:
			right = active;
			break;
		}
	}
	
	@Override
	public void updateComponent()
	{
		double elapsedTime;
		
		try {
			elapsedTime = 1000_000_000 / player.game.fps;
		} 
		catch (Exception e) 
		{
			elapsedTime = 0;
		}
		
		if(up | down | left | right)
		{
			distanceSum += (speed * elapsedTime)/1_000_000;
			
			if(distanceSum < 1)
				return;

			distanceSum -= (int)distanceSum;

			if(up)
				((PlayerShape)player.shape).translate(0, -1);
			if(down)
				((PlayerShape)player.shape).translate(0, 1);
			if(left)
				((PlayerShape)player.shape).translate(-1, 0);
			if(right)
				((PlayerShape)player.shape).translate(1, 0);
		}
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
