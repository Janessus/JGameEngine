import java.awt.Graphics;
import java.awt.Point;

import gameObject.components.IGameObjectComponent;

public interface IGameObject
{	
	public Point getPosition();
	public void paintObject(Graphics g);
	public void addComponent(IGameObjectComponent c);
	public void updateObject();
}
