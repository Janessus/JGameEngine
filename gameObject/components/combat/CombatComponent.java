package gameObject.components.combat;
import java.awt.Color;
import java.awt.Graphics;

import core.Direction;
import gameObject.GameObject;
import gameObject.components.GameObjectComponent;
import gameObject.components.attributeComponent.HealthAttributeComponent;
import templates.Armor;
import templates.Weapon;

public class CombatComponent extends GameObjectComponent
{
	private GameObject parent;
	private Direction attackDirection;
	private boolean attacking;
	private Weapon equippedWeapon;
	private Armor equippedArmor;
	private boolean visible;
	
	
	public CombatComponent(GameObject parent)
	{
		this.parent = parent;
		attacking = false;
		visible = false;
	}
	
	/*
	private boolean canAttack(GameObject o)
	{
	-----> if(o.shape.getCircleEdgeDistance(this.parent.shape) <= maxAttackRange) <-------
		{
			CombatComponent enemy = (CombatComponent) o.getFirstComponent(CombatComponent.class);
			if(enemy != null)
				return true;
		}
		return false;
	}
	
	*/
	

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
		System.out.println(this.parent + " getting attacked by " + combat.parent);
		double damage = 0;

		if(equippedArmor != null)
		{
			damage = combat.getWeapon().getDamage() * equippedArmor.getReductionFactor();
			damage = equippedArmor.getHit(damage);
		}
		else
			damage = combat.getWeapon().getDamage();
		((HealthAttributeComponent)parent.getFirstComponent(HealthAttributeComponent.class)).add((int)-damage);
	}
	
	
	public void handleAttack(Weapon weapon)
	{
		System.out.println(this.parent + " getting attacked by " + weapon);
		double damage = 0;

		if(equippedArmor != null)
			damage = weapon.getDamage() * equippedArmor.getReductionFactor();
		else
			damage = weapon.getDamage();
		((HealthAttributeComponent)parent.getFirstComponent(HealthAttributeComponent.class)).add((int)-damage);
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
	

	@Override
	public void drawComponent(Graphics g)
	{
		if(visible && equippedWeapon != null)
		{
			//maxAttackRange
			g.setColor(Color.green);
			g.drawOval((int)(parent.getShape().getPosition().getX() - equippedWeapon.getRange() + parent.getShape().getSize().getWidth()/2)
					, (int)(parent.getShape().getPosition().getY() - equippedWeapon.getRange() + parent.getShape().getSize().getHeight()/2)
					, (int)(equippedWeapon.getRange())*2
					, (int)(equippedWeapon.getRange())*2);
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

	public GameObject getParent()
	{
		return parent;
	}
}
