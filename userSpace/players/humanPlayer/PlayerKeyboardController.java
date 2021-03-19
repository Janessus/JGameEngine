package userSpace.players.humanPlayer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

import core.Direction;
import core.Game;
import core.Movements;
import gameObject.GameObject;
import gameObject.components.GameObjectComponent;

public class PlayerKeyboardController extends GameObjectComponent
{
	private boolean up, down, left, right; //movement
	private double speed = 0.5;
	private JFrame window;

	
	public PlayerKeyboardController(GameObject parent)
	{
		super(parent);
		this.window = parent.getGame().getWindow();
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
		double elapsedTime; //in ns
		double tmpX = 0, tmpY = 0;
		
		Direction targetDirection;
		
		try {
			elapsedTime = 1_000_000_000 / Game.getFPS();
		} 
		catch (Exception e) 
		{
			elapsedTime = 0;
		}
		
		if(up | down | left | right)
		{
			if(up)
				tmpY -= 1;
			if(down)
				tmpY += 1;
			if(left)
				tmpX -= 1;
			if(right)
				tmpX += 1;
		}
		
		targetDirection = new Direction(tmpX, tmpY).normalize();
		
		if(targetDirection != null)
		{	
			double distanceFactor = (speed * elapsedTime)/1_000_000;
			getParent().getShape().translate(distanceFactor * targetDirection.getX(), distanceFactor * targetDirection.getY());
		}
	}

	@Override
	public void drawComponent(Graphics g)
	{
		// TODO Auto-generated method stub
	}
}
