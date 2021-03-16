import java.awt.Color;
import java.awt.Graphics;

public class CombatComponent implements IGameObjectComponent
{
	private GameObject parent;
	private double maxAttackRange = 50;
	private Weapon equippedWeapon;
	private Armor equippedArmor;
	private boolean visible;
	
	public CombatComponent(GameObject parent)
	{
		this.parent = parent;
	}
	
	private boolean canAttack(GameObject o)
	{
		if(o.shape.getCircleEdgeDistance(this.parent.shape) <= maxAttackRange && equippedWeapon.isAttackReady())
		{
			CombatComponent enemy = (CombatComponent) o.getFirstComponent(CombatComponent.class);
			if(enemy != null)
				return true;
		}
		return false;
	}
	
	public void attack(GameObject o)
	{
		if(o != null && canAttack(o))
		{
			System.out.println(this.parent + " attacking " + o);
			equippedWeapon.attack((CombatComponent)o.getFirstComponent(CombatComponent.class));
		}
	}
	
	
	public void attack(Direction direction)
	{
		if(direction != null && equippedWeapon != null)
		{
			equippedWeapon.attack(direction);
		}
	}
	
	
	
	public void handleAttack(CombatComponent combat)
	{
		System.out.println(this.parent + " getting attacked by " + combat.parent);
		double damage = 0;
		ArmorAttributeComponent armor = (ArmorAttributeComponent) parent.getFirstComponent(ArmorAttributeComponent.class);
		if(armor != null)
			damage = combat.getWeapon().getDamage() * armor.getReductionFactor();
		else
			damage = combat.getWeapon().getDamage();
		((HealthAttributeComponent)parent.getFirstComponent(HealthAttributeComponent.class)).add((int)-damage);
		
	}

	public Weapon getWeapon()
	{
		return equippedWeapon;
	}

	
	@Override
	public void updateComponent()
	{

	}

	@Override
	public void drawComponent(Graphics g)
	{
		if(visible)
		{
			//maxAttackRange
			g.setColor(Color.green);
			g.drawOval((int)(parent.shape.getPosition().getX() - maxAttackRange + parent.shape.getSize().getWidth()/2)
					, (int)(parent.shape.getPosition().getY() - maxAttackRange + parent.shape.getSize().getHeight()/2)
					, (int)(maxAttackRange)*2
					, (int)(maxAttackRange)*2);
			
		}
	}

	@Override
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	public void equipArmor(Armor armor)
	{
		this.equippedArmor = armor;
	}
	
	public void equipWeapon(Weapon weapon)
	{
		equippedWeapon = weapon;
	}

	public Armor getArmor()
	{
		return equippedArmor;
	}

	public void destroyArmor()
	{
		equippedArmor = null;
	}

	
}
