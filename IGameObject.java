import java.awt.Graphics;
import java.awt.Point;

import gameObject.components.GameObjectComponent;

public interface IGameObject
{	
	public Point getPosition();
	public void paintObject(Graphics g);
	public void addComponent(GameObjectComponent c);
	public void updateObject();
}
