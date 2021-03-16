
public class ArtificialPlayer extends GameObject
{

	ArtificialPlayer(Game game)
	{
		super(game, new ArtificialPlayerShape());
		
		addComponent(new ArtificialPlayerController(this));
		
		addComponent(new Collider(this));
		game.subscribeCollisions(this);
		
		addComponent(new HealthAttributeComponent(this, 100));
		addComponent(new CombatComponent(this));
		addComponent(new CollectorComponent(this));
		
		getFirstComponent(HealthAttributeComponent.class).setVisible(true);
	}
}
