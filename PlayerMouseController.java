import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class PlayerMouseController implements IGameObjectComponent
{
	private JFrame window;
	private GameObject player;
	private boolean attacking;
	private boolean visible;
	
	
	public PlayerMouseController(GameObject parent)
	{
		this.player = parent;
		this.window = parent.game.window;
		this.attacking = false;
		this.visible = false;
		setupListeners();
	}
	
	private void setupListeners()
	{
		window.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
				attacking = true;

			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				attacking = false;
			}
			
			
		});
	}
	
	@Override
	public void updateComponent()
	{
		if(attacking)
		{
			Direction direction = Direction.getDirection(player.shape.getCenter(), player.game.mousePos);
			((CombatComponent)player.getFirstComponent(CombatComponent.class)).attack(direction);	
		}
	}

	@Override
	public void drawComponent(Graphics g)
	{
		if(visible)
		{
			g.setColor(Color.gray);
			Direction direction = Direction.getDirection(player.shape.getCenter(), player.game.window.getMousePosition());
			g.fillOval((int)(player.shape.getCenter().getX() + direction.getX() * player.shape.getSize().getWidth() * 0.7)-4, (int)(player.shape.getCenter().getY() + direction.getY() * player.shape.getSize().getHeight() * 0.7)-4, 8, 8);
		}
	}

	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

}
