
public abstract class Weapon
{
	private int damage;
	private int durability;
	private int range;
	private double attackSpeed;
	private long attackTime;
	private CombatComponent owner;
	
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
	
	public boolean isAttackReady()
	{
		if((getTimeSinceAttack() >= (1/attackSpeed) * 1_000_000_000) && durability > 0)
			return true;
		return false;
	}
	
	public void attack(CombatComponent c)
	{
		if(c != null && isAttackReady())
		{
			attackTime = System.nanoTime();
			c.handleAttack(owner);
		}	
	}
	
	public double getDamage()
	{
		return damage;
	}

	public abstract void attack(Direction direction);
}
