package userSpace.items;
import templates.Armor;

public class IronArmor extends Armor
{
	public IronArmor(int durability, double reductionFactor)
	{
		super(reductionFactor);
		setDurability(durability);
	}
}
