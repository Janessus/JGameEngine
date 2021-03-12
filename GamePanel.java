import java.awt.Container;

import javax.swing.JFrame;

public class GamePanel
{
	public Container gameObjects;
	
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Game");
		Game game = new Game(window);
		
		game.init();
		
		window.add(game);
		window.setSize(1000, 700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(game);
		window.setVisible(true);
		
		while(true)
		{
			game.update();
			game.repaint();
		}
	}

}
