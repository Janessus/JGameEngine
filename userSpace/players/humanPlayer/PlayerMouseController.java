package userSpace.players.humanPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import core.Direction;
import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import gameObject.components.combat.CombatComponent;

public class PlayerMouseController extends GameObjectComponent
{
	private JFrame window;
	private Direction mouseDirection;
	private boolean attacking;
	
	
	public PlayerMouseController(GameObject parent)
	{
		super(parent);
		this.window = parent.getGame().getWindow();
		this.attacking = false;
		mouseDirection = new Direction();
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
				((CombatComponent)getParent().getFirstComponent(CombatComponent.class)).setAttacking(true, mouseDirection);	
			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				((CombatComponent)getParent().getFirstComponent(CombatComponent.class)).setAttacking(false, mouseDirection);	
			}
		});
	}
	
	@Override
	public void updateComponent()
	{
		Direction tmp = Direction.getDirection(getParent().getShape().getCenter(), getParent().getGame().getMousePos());
		if(tmp != null)
			mouseDirection.setDirection(tmp.getX(), tmp.getY());

		if(attacking)
		{
			
		}
	}

	@Override
	public void drawComponent(Graphics2D g)
	{
		if(isVisible())
		{
			g.setColor(Color.gray);
//			Direction mouseDirection = Direction.getDirection(getParent().shape.getCenter(), getParent().game.window.getMousePosition());
			g.fillOval((int)(getParent().getShape().getCenter().getX() + mouseDirection.getX() * getParent().getShape().getSize().getWidth() * 0.7)-4, (int)(getParent().getShape().getCenter().getY() + mouseDirection.getY() * getParent().getShape().getSize().getHeight() * 0.7)-4, 8, 8);
		}
	}
}
