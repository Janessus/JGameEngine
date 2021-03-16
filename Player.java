

public class Player extends GameObject
{	
	public Player(Game game)
	{
		super(game, new PlayerShape());
		
		addComponent(new PlayerKeyboardController(this));
		
		addComponent(new Collider(this));
		game.subscribeCollisions(this);
		
		addComponent(new HealthAttributeComponent(this, 100));
		addComponent(new StaminaAttributeComponent(this, 100));
		addComponent(new CollectorComponent(this));
		addComponent(new CombatComponent(this));
		
		getFirstComponent(HealthAttributeComponent.class).setVisible(true);
		getFirstComponent(StaminaAttributeComponent.class).setVisible(true);
	}
}
