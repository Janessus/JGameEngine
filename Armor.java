
public abstract class Armor
{
	protected int durability;
	private double damageReductionFactor;
	private CombatComponent owner;
	
	public Armor(double reductionFactor)
	{
		this.damageReductionFactor = reductionFactor;
		this.durability = 100;
	}

	//decreases the durability of the armor and destroys it if below 0.
	//returns the remaining damage
	public double getHit(double damage)
	{
		durability -= damage * damageReductionFactor;
		if(durability <= 0)
			owner.destroyArmor();
		return damage * damageReductionFactor;
	}
	
	public void setOwnerComponent(CombatComponent c)
	{
		this.owner = c;
	}
	
}
