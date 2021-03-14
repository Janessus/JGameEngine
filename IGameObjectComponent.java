import java.awt.Graphics;

public interface IGameObjectComponent
{
	public void updateComponent();
	public void drawComponent(Graphics g);
	void setVisible(boolean visible);
}
