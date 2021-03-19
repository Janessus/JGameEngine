package gameObject.components.attributeComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gameObject.GameObject;

public class ArmorAttributeComponent extends AttributeComponent
{
	private boolean visible;
	
	
	public ArmorAttributeComponent(GameObject parent, int maxArmor)
	{
		super(parent, maxArmor);
		visible = false;
		this.setValue(maxArmor);
	}
	
	
	@Override
	public void updateComponent(){}

	
	@Override
	public void drawComponent(Graphics g)
	{
		if(visible)
		{
			g.setColor(Color.gray);
			g.fillRect((int)getParent().getShape().getPosition().getX(), (int)getParent().getShape().getPosition().getY() + getParent().getShape().getSize().height + 16, (int)((double)getValue()/(double)getMaxValue() * ((double)getParent().getShape().getSize().width)), 5);
			g.setColor(Color.BLACK);
			g.drawRect((int)getParent().getShape().getPosition().getX(), (int)getParent().getShape().getPosition().getY() +  getParent().getShape().getSize().height + 16, getParent().getShape().getSize().width, 5);
		}
	}

	
	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}


	@Override
	protected boolean alreadyFull()
	{
		return false;
	}


	@Override
	protected boolean belowZero()
	{
		return false;
	}
}
