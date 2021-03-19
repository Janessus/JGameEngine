package gameObject.components.combat;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import core.Direction;
import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import gameObject.components.attributeComponent.HealthAttributeComponent;
import templates.Armor;
import templates.Weapon;

public class CombatComponent extends GameObjectComponent
{
	private Direction attackDirection;
	private boolean attacking;
	private Weapon equippedWeapon;
	private Armor equippedArmor;
	
	
	public CombatComponent(GameObject parent)
	{
		super(parent);
		attacking = false;
	}
	

	public void attack(Direction direction)
	{
		if(direction != null && equippedWeapon != null)
		{
			if(equippedWeapon.attack(direction))
			{
				
			}
		}
	}
	
	
	public void handleAttack(CombatComponent combat)
	{
		double damage = 0;

		if(equippedArmor != null)
		{
			damage = combat.getWeapon().getDamage() * equippedArmor.getReductionFactor();
			damage = equippedArmor.getHit(damage);
		}
		else
			damage = combat.getWeapon().getDamage();
		((HealthAttributeComponent)getParent().getFirstComponent(HealthAttributeComponent.class)).add((int)-damage);
	}
	
	
	public void handleAttack(Weapon weapon)
	{
		System.out.println(this.getParent() + " getting attacked by " + weapon);
		double damage = 0;

		if(equippedArmor != null)
			damage = weapon.getDamage() * equippedArmor.getReductionFactor();
		else
			damage = weapon.getDamage();
		((HealthAttributeComponent)getParent().getFirstComponent(HealthAttributeComponent.class)).add((int)-damage);
	}
	
	
	public Weapon getWeapon()
	{
		return equippedWeapon;
	}

	
	@Override
	public void updateComponent()
	{
		if(attacking && attackDirection != null)
		{
			attack(attackDirection);
		}
	}
	

	public void drawComponent(Graphics g)
	{
		if(isVisible() && equippedWeapon != null)
		{
			//maxAttackRange
			g.setColor(Color.green);
			g.drawOval((int)(getParent().getShape().getPosition().getX() - equippedWeapon.getRange() + getParent().getShape().getSize().getWidth()/2)
					, (int)(getParent().getShape().getPosition().getY() - equippedWeapon.getRange() + getParent().getShape().getSize().getHeight()/2)
					, (int)(equippedWeapon.getRange())*2
					, (int)(equippedWeapon.getRange())*2);
		}
		//TODO draw attack animation
	}

	
	public void equipArmor(Armor armor)
	{
		this.equippedArmor = armor;
	}
	
	
	public void equipWeapon(Weapon weapon)
	{
		equippedWeapon = weapon;
		equippedWeapon.setOwner(this);
	}

	
	public Armor getArmor()
	{
		return equippedArmor;
	}

	
	public void destroyArmor()
	{
		equippedArmor = null;
	}


	public void setAttacking(boolean b, Direction direction)
	{
		attacking = b;
		attackDirection = direction;
		if(b == false)
			attackDirection = null;
	}
}
