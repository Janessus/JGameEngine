package templates;
import java.awt.Graphics;
import java.util.ArrayList;

import core.Direction;
import gameObject.components.combat.CombatComponent;

public abstract class Weapon
{
	private int damage;
	private int durability;
	private int range;
	private double attackSpeed;
	private long attackTime;
	private CombatComponent owner;
	protected boolean playanimation;
	
	public abstract ArrayList<CombatComponent> getEnemiesInAttackBox(Direction mouseDirection);
	public abstract void playAnimation(Graphics g);

	
	public Weapon(int damage, double attackSpeed, int range, int durability) //TODO implement range
	{
		this.damage = damage;
		this.durability = durability;
		this.setRange(range);
		this.attackSpeed = attackSpeed;
		this.playanimation = false;
		attackTime = System.nanoTime();
	}
	
	
	public CombatComponent getOwner()
	{
		return owner;
	}
	
	
	public void setOwner(CombatComponent c)
	{
		this.owner = c;
	}
	
	
	private long getTimeSinceAttack()
	{
		return System.nanoTime() - attackTime;
	}
	
	
	private boolean isAttackReady()
	{
		if((getTimeSinceAttack() >= (1/attackSpeed) * 1_000_000_000) && durability > 0)
			return true;
		return false;
	}
	
	
	private void sendAttackMessage(CombatComponent c)
	{
		if(c != null)
		{
			c.handleAttack(owner);
		}	
	}
	
	private void setAnimationPlaying(boolean b)
	{
		//playAnimation = b;
	}
	
	public boolean attack(Direction direction)
	{
		ArrayList<CombatComponent> enemies = getEnemiesInAttackBox(direction);
		if(enemies != null && isAttackReady())
		{
			attackTime = System.nanoTime();
			
			System.out.println(owner + " is attacking: " + enemies.get(0).getParent());
			
			for(int i = 0; i < enemies.size(); i++)
			{
				sendAttackMessage(enemies.get(i));
			}
			setAnimationPlaying(true);
			return true;
		}
		return false;
	}
	
	
	public double getDamage()
	{
		return damage;
	}
	
	public int getRange()
	{
		return range;
	}
	
	public void setRange(int range)
	{
		this.range = range;
	}
}
