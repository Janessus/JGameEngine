
public abstract class AttributeComponent implements IGameObjectComponent
{
	protected GameObject parent;
	protected int maxValue;
	protected int value;
	
	protected abstract boolean alreadyFull();
	protected abstract boolean belowZero();
	
	public AttributeComponent(GameObject parent, int maxValue)
	{
		this.parent = parent;
		this.maxValue = maxValue;
		this.value = maxValue;
	}
	
	
	public boolean add(int amount)
	{
		if(amount > 0 && value == maxValue)
			return alreadyFull();

		if(value + amount <= 0)
			return belowZero();
		
		if(value + amount <= maxValue)
			value += amount;
		else
			value = maxValue;
		return true;
	}
	
	public String toString()
	{
		return "(" + value + "/" + maxValue + ")";
	}
}
