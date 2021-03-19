package gameObject.components;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gameObject.GameObject;

public abstract class GameObjectComponent
{
	private GameObject parent;
	private boolean visible;
	
	public abstract void updateComponent();
	public abstract void drawComponent(Graphics g);
	
	public GameObjectComponent(GameObject parent)
	{
		this.parent = parent;
		visible = false;
	}
	
	public GameObject getParent()
	{
		return parent;
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	public boolean isVisible()
	{
		return visible;
	}
}
