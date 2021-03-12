import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public interface IGameObject
{
	public List<IGameObjectComponent> components = new ArrayList<IGameObjectComponent>();
	
	public Point getPosition();
	public void paintObject(Graphics g);
	public void addComponent(IGameObjectComponent c);
	public void updateObject();
}
