package gameObject.components;
import java.awt.Graphics;

public abstract class GameObjectComponent
{
	public abstract void updateComponent();
	public abstract void drawComponent(Graphics g);
	public abstract void setVisible(boolean visible);
}
