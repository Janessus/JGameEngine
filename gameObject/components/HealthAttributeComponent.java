package gameObject.components;
import java.awt.Color;
import java.awt.Graphics;

import gameObject.GameObject;

public class HealthAttributeComponent extends AttributeComponent
{
	private boolean visible;
	
	public HealthAttributeComponent(GameObject parent, int maxHealth)
	{
		super(parent, maxHealth);
		visible = false;
		setValue(maxHealth);
	}
	
	
	@Override
	protected boolean alreadyFull()
	{
		return false;
	}
	
	
	@Override
	protected boolean belowZero()
	{
		getParent().destroy();
		return true;
	}
	
	
	@Override
	public void updateComponent() {}

	
	@Override
	public void drawComponent(Graphics g)
	{
		if(visible)
		{
			g.setColor(Color.RED);
			g.fillRect((int)getParent().getShape().getPosition().getX(), (int)getParent().getShape().getPosition().getY() + getParent().getShape().getSize().height + 4, getParent().getShape().getSize().width, 5);
			g.setColor(Color.GREEN);
			g.fillRect((int)getParent().getShape().getPosition().getX(), (int)getParent().getShape().getPosition().getY() + getParent().getShape().getSize().height + 4, (int)((double)getValue()/(double)getMaxValue() * ((double)getParent().getShape().getSize().width)), 5);
			g.setColor(Color.BLACK);
			g.drawRect((int)getParent().getShape().getPosition().getX(), (int)getParent().getShape().getPosition().getY() +  getParent().getShape().getSize().height + 4, getParent().getShape().getSize().width, 5);
		}
	}
	
	
	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
}
