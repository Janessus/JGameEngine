

public class Player extends GameObject
{	
	public Player(Game game, String name)
	{
		super(game, new PlayerShape());
		
		addComponent(new PlayerKeyboardController(this, game.window));
		addComponent(new Collider(this));
		addComponent(new CollectorComponent(this));
		
		//Physics p = new Physics(this, game);
		//addComponent(p);
		//addComponent(new PlayerPhysicsController(this, game.window, p));
	}
}
