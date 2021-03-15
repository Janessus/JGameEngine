import java.awt.Color;
import java.awt.Graphics;

public class HealthAttributeComponent extends AttributeComponent
{
	private boolean visible;
	
	public HealthAttributeComponent(GameObject parent, int maxHealth)
	{
		super(parent, maxHealth);
		visible = false;
		this.value = maxHealth;
	}
	
	
	@Override
	protected boolean alreadyFull()
	{
		return false;
	}
	
	
	@Override
	protected boolean belowZero()
	{
		parent.destroy();
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
			g.fillRect(parent.shape.getPosition().x, parent.shape.getPosition().y + parent.shape.getSize().height + 4, parent.shape.getSize().width, 5);
			g.setColor(Color.GREEN);
			g.fillRect(parent.shape.getPosition().x, parent.shape.getPosition().y + parent.shape.getSize().height + 4, (int)((double)value/(double)maxValue * ((double)parent.shape.getSize().width)), 5);
			g.setColor(Color.BLACK);
			g.drawRect(parent.shape.getPosition().x, parent.shape.getPosition().y +  parent.shape.getSize().height + 4, parent.shape.getSize().width, 5);
		}
	}

	
	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
		add(-40);
	}
}
