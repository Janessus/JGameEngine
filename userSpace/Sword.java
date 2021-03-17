package userSpace;
import java.awt.Graphics;
import java.util.ArrayList;

import gameObject.GameObject;
import gameObject.components.CombatComponent;
import templates.Direction;
import templates.Weapon;

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
		for(int i = 0; i < getOwner().getParent().getGame().getGameObjects().size(); i++)
		{	
			GameObject go = getOwner().getParent().getGame().getGameObjects().get(i);
			if(!go.equals(getOwner().getParent()))
			{
				if(go.getShape().getCircleEdgeDistance(getOwner().getParent().getShape()) <= getRange())
				{
					if(Math.abs(mouseDirection.getAngle(Direction.getDirection(getOwner().getParent().getPosition(), go.getPosition()))) <= attackAngle/2)
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
