import java.awt.Graphics;
import java.awt.Point;

public interface IGameObject
{	
	public Point getPosition();
	public void paintObject(Graphics g);
	public void addComponent(IGameObjectComponent c);
	public void updateObject();
}
