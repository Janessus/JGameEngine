package templates;
import gameObject.components.CombatComponent;

public abstract class Armor
{
	private int durability;
	private double damageReductionFactor;
	private CombatComponent owner;
	
	
	public Armor(double reductionFactor)
	{
		this.damageReductionFactor = reductionFactor;
		this.durability = 100;
	}
	
	
	public double getReductionFactor()
	{
		return damageReductionFactor;
	}

	
	//decreases the durability of the armor and destroys it if below 0.
	//returns the remaining damage
	public double getHit(double damage)
	{
		double restDamage = 0;
		durability -= damage * damageReductionFactor;
		if(durability <= 0)
		{
			restDamage = Math.abs(durability);
			durability = 0;
			owner.destroyArmor();
		}
		((ArmorAttributeComponent)owner.getParent().getFirstComponent(ArmorAttributeComponent.class)).setValue(durability);
		return restDamage;
	}
	
	
	public void setOwnerComponent(CombatComponent c)
	{
		this.owner = c;
	}
	
	
	public int getDurability()
	{
		return durability;
	}


	public void setDurability(int durability)
	{
		this.durability = durability;
	}
}
