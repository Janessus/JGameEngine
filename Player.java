

public class Player extends GameObject
{	
	public Player(Game game)
	{
		super(game, new PlayerShape());
		
		addComponent(new PlayerKeyboardController(this));
		addComponent(new PlayerMouseController(this));
		
		addComponent(new Collider(this));
		game.subscribeCollisions(this);
		
		addComponent(new HealthAttributeComponent(this, 100));
		addComponent(new StaminaAttributeComponent(this, 100));
		addComponent(new CollectorComponent(this));
		addComponent(new CombatComponent(this));
		
		((CombatComponent)getFirstComponent(CombatComponent.class)).equipWeapon(new Sword(50, 5, 100, 100));
		
		getFirstComponent(PlayerMouseController.class).setVisible(true);
		getFirstComponent(HealthAttributeComponent.class).setVisible(true);
		getFirstComponent(StaminaAttributeComponent.class).setVisible(true);
		getFirstComponent(CombatComponent.class).setVisible(true);
	}
}
