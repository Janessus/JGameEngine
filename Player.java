import java.util.Date;

public class Player extends GameObject
{
	public Game game;
	
	public Player(String name, Game game)
	{
		super(new PlayerShape());
		this.game = game;
		
		addComponent(new PlayerKeyboardController(this, game.window));
		
		//Physics p = new Physics(this, game);
		//addComponent(p);
		//addComponent(new PlayerPhysicsController(this, game.window, p));
	}
	
	@Override
	public void updateObject()
	{
		super.updateObject();
	}
}
