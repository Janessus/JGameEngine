import java.awt.Graphics;
import java.util.ArrayList;

public class Sword extends Weapon
{
	private double attackAngle = 100;
	public Sword(int damage, double attackSpeed, int range, int durability)
	{
		super(damage, attackSpeed, range, durability);
	}

	@Override
	public ArrayList<CombatComponent> getEnemiesInAttackBox(Direction mouseDirection)
	{
		ArrayList<CombatComponent> enemies = new ArrayList<CombatComponent>();
		for(int i = 0; i < owner.parent.game.gameObjects.size(); i++)
		{	
			GameObject go = owner.parent.game.gameObjects.get(i);
			if(!go.equals(owner.parent))
			{
				if(go.shape.getCircleEdgeDistance(owner.parent.shape) <= range)
				{
					if(Math.abs(mouseDirection.getAngle(Direction.getDirection(owner.parent.getPosition(), go.getPosition()))) <= attackAngle/2)
					{
						CombatComponent combat = (CombatComponent)go.getFirstComponent(CombatComponent.class);
						if(combat != null)
							enemies.add(combat);
					}
				}
			}
		}
		if(enemies.isEmpty())
			return null;
		return enemies;
	}

	@Override
	public void playAnimation(Graphics g)
	{
		// TODO Auto-generated method stub
		
	}
}
