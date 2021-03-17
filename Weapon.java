import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Weapon
{
	private int damage;
	private int durability;
	protected int range;
	private double attackSpeed;
	private long attackTime;
	CombatComponent owner;
	
	protected int frameCount;
	
	public abstract ArrayList<CombatComponent> getEnemiesInAttackBox(Direction mouseDirection);
	public abstract void playAnimation(Graphics g);
	

	
	public Weapon(int damage, double attackSpeed, int range, int durability) //TODO implement range
	{
		this.damage = damage;
		this.durability = durability;
		this.range = range;
		this.attackSpeed = attackSpeed;
		attackTime = System.nanoTime();
	}
	
	
	public void setOwnerComponent(CombatComponent c)
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
	
	
	public boolean attack(Direction direction)
	{
		ArrayList<CombatComponent> enemies = getEnemiesInAttackBox(direction);
		if(enemies != null && isAttackReady())
		{
			attackTime = System.nanoTime();
			
			System.out.println(owner + " is attacking: " + enemies.get(0).parent);
			
			for(int i = 0; i < enemies.size(); i++)
			{
				sendAttackMessage(enemies.get(i));
			}
			return true;
		}
		return false;
	}
	
	
	public double getDamage()
	{
		return damage;
	}
}
