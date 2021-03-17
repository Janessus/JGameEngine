import java.awt.Color;
import java.awt.Graphics;

public class ArmorAttributeComponent extends AttributeComponent
{
	private boolean visible;
	private int value;
	
	
	public ArmorAttributeComponent(GameObject parent, int maxArmor)
	{
		super(parent, maxArmor);
		visible = false;
		this.setValue(maxArmor);
	}
	
	
	public double getReductionFactor()
	{
		return 1;
	}

	
	@Override
	public void updateComponent()
	{

	}

	
	@Override
	public void drawComponent(Graphics g)
	{
		if(visible)
		{
			g.setColor(Color.gray);
			g.fillRect((int)parent.shape.getPosition().getX(), (int)parent.shape.getPosition().getY() + parent.shape.getSize().height + 16, (int)((double)getValue()/(double)maxValue * ((double)parent.shape.getSize().width)), 5);
			g.setColor(Color.BLACK);
			g.drawRect((int)parent.shape.getPosition().getX(), (int)parent.shape.getPosition().getY() +  parent.shape.getSize().height + 16, parent.shape.getSize().width, 5);
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
		return true;
	}


	public int getValue()
	{
		return value;
	}


	public void setValue(int value)
	{
		this.value = value;
	}
}
