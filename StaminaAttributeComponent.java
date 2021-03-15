import java.awt.Color;
import java.awt.Graphics;


public class StaminaAttributeComponent extends AttributeComponent
{
	
	private boolean visible;
	
	public StaminaAttributeComponent(GameObject parent, int maxStamina)
	{
		super(parent, maxStamina);
		visible = false;
		this.value = maxStamina;
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
	public void drawComponent(Graphics g)
	{
		if(visible)
		{
			g.setColor(Color.RED);
			g.fillRect(parent.shape.getPosition().x, parent.shape.getPosition().y + parent.shape.getSize().height + 10, parent.shape.getSize().width, 5);
			g.setColor(Color.YELLOW);
			g.fillRect(parent.shape.getPosition().x, parent.shape.getPosition().y + parent.shape.getSize().height + 10, (int)((double)value/(double)maxValue * ((double)parent.shape.getSize().width)), 5);
			g.setColor(Color.BLACK);
			g.drawRect(parent.shape.getPosition().x, parent.shape.getPosition().y +  parent.shape.getSize().height + 10, parent.shape.getSize().width, 5);
		}
	}

	
	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
		add(-40);
	}
}

