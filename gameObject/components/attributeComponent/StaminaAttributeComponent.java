package gameObject.components.attributeComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gameObject.GameObject;


public class StaminaAttributeComponent extends AttributeComponent
{	
	public StaminaAttributeComponent(GameObject parent, int maxStamina)
	{
		super(parent, maxStamina);
	}
	
	
	@Override
	protected boolean alreadyFull()
	{
		return false;
	}
	
	
	@Override
	protected boolean belowZero()
	{
		return true;
	}
	
	
	@Override
	public void updateComponent() {}

	
	@Override
	public void drawComponent(Graphics2D g)
	{
		if(isVisible())
		{
			g.setColor(Color.RED);
			g.fillRect((int)getParent().getShape().getPosition().getX(), (int)getParent().getShape().getPosition().getY() + getParent().getShape().getSize().height + 10, getParent().getShape().getSize().width, 5);
			g.setColor(Color.YELLOW);
			g.fillRect((int)getParent().getShape().getPosition().getX(), (int)getParent().getShape().getPosition().getY() + getParent().getShape().getSize().height + 10, (int)((double)getValue()/(double)getMaxValue() * ((double)getParent().getShape().getSize().width)), 5);
			g.setColor(Color.BLACK);
			g.drawRect((int)getParent().getShape().getPosition().getX(), (int)getParent().getShape().getPosition().getY() +  getParent().getShape().getSize().height + 10, getParent().getShape().getSize().width, 5);
		}
	}
}

