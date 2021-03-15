

public class Player extends GameObject
{	
	public Player(Game game, String name)
	{
		super(game, new PlayerShape());
		
		addComponent(new PlayerKeyboardController(this, game.window));
		addComponent(new Collider(this));
		addComponent(new HealthAttributeComponent(this, 100));
		addComponent(new StaminaAttributeComponent(this, 100));
		addComponent(new CollectorComponent(this));
		
		getFirstComponent(HealthAttributeComponent.class).setVisible(true);
		getFirstComponent(StaminaAttributeComponent.class).setVisible(true);
		
		//Physics p = new Physics(this, game);
		//addComponent(p);
		//addComponent(new PlayerPhysicsController(this, game.window, p));
	}
}
