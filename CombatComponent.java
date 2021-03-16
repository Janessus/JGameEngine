import java.awt.Graphics;

public class CombatComponent implements IGameObjectComponent
{
	private GameObject parent;
	private double maxAttackRange;
	
	public CombatComponent(GameObject parent)
	{
		this.parent = parent;
	}
	
	private boolean shouldAttack(GameObject o)
	{
		if(o.shape.getCircleEdgeDistance(this.parent.shape) <= maxAttackRange)
		{
			CombatComponent enemy = (CombatComponent) o.getFirstComponent(CombatComponent.class);
			if(enemy != null)
			{
				return true;
			}
		}
		return false;
	}
	
	private void attack(GameObject o)
	{
		if(shouldAttack(o))
		{
			((CombatComponent)parent.getFirstComponent(CombatComponent.class)).handleAttack(this);
		}
	}
	
	public void handleAttack(CombatComponent combat)
	{
		
	}

	@Override
	public void updateComponent()
	{
		// TODO Auto-generated method stub
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
