package gameObject.components;
import gameObject.GameObject;

public interface ICollisionHandler
{
	public boolean handleCollisionWith(GameObject o);
}
