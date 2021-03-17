package userSpace.players.artificialPlayer;
import core.Game;
import gameObject.GameObject;
import gameObject.components.armor.ArmorAttributeComponent;
import gameObject.components.attributeComponent.HealthAttributeComponent;
import gameObject.components.collectable.CollectorComponent;
import gameObject.components.collider.Collider;
import gameObject.components.combat.CombatComponent;
import userSpace.items.IronArmor;
import userSpace.items.Sword;

public class ArtificialPlayer extends GameObject
{

	public ArtificialPlayer(Game game)
	{
		super(game, new ArtificialPlayerShape());
		
		addComponent(new ArtificialPlayerController(this));
		
		addComponent(new Collider(this));
		game.subscribeCollisions(this);
		
		addComponent(new HealthAttributeComponent(this, 100));
		addComponent(new ArmorAttributeComponent(this, 100));
		addComponent(new CombatComponent(this));
		addComponent(new CollectorComponent(this));
		
		getFirstComponent(HealthAttributeComponent.class).setVisible(true);
		
		CombatComponent combat = (CombatComponent)getFirstComponent(CombatComponent.class);
		
		combat.equipWeapon(new Sword(20, 0.5, 50, 100));
		combat.getWeapon().setOwner(combat);
		
		combat.equipArmor(new IronArmor(100, 0.5));
		combat.getArmor().setOwnerComponent(combat);
		
		combat.setVisible(true);
		
		getFirstComponent(ArtificialPlayerController.class).setVisible(true);
		getFirstComponent(ArmorAttributeComponent.class).setVisible(true);
	}
}
