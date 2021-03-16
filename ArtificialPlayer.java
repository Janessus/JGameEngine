
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
		
		CombatComponent combat = (CombatComponent)getFirstComponent(CombatComponent.class);
		
		combat.equipWeapon(new Sword(20, 0.5, 20, 100));
		combat.getWeapon().setOwnerComponent(combat);
		
		combat.equipArmor(new IronArmor(50, 100));
		combat.getArmor().setOwnerComponent(combat);
		
		combat.setVisible(true);
	}
}
