package gameObject.components.attributeComponent;
import gameObject.GameObject;
import gameObject.components.GameObjectComponent;

public abstract class AttributeComponent extends GameObjectComponent
{
	private int maxValue;
	private int value;
	
	protected abstract boolean alreadyFull();
	protected abstract boolean belowZero();
	
	
	public AttributeComponent(GameObject parent, int maxValue)
	{
		super(parent);
		this.maxValue = maxValue;
		this.value = maxValue;
	}
	
	
	public boolean add(int amount)
	{
		if(amount > 0 && getValue() == getMaxValue())
			return alreadyFull();

		if(getValue() + amount <= 0)
			return belowZero();
		
		if(getValue() + amount <= getMaxValue())
			value = getValue() + amount;
		else
			value = getMaxValue();
		return true;
	}
	
	
	public String toString()
	{
		return "(" + getValue() + "/" + getMaxValue() + ")";
	}
	
	
	public int getValue()
	{
		return value;
	}
	
	
	public void setValue(int value)
	{
		this.value = value;
	}
	

	public int getMaxValue()
	{
		return maxValue;
	}
}
