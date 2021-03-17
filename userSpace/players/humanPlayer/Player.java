package userSpace.players.humanPlayer;
import core.Game;
import gameObject.GameObject;
import gameObject.components.armor.ArmorAttributeComponent;
import gameObject.components.attributeComponent.HealthAttributeComponent;
import gameObject.components.attributeComponent.StaminaAttributeComponent;
import gameObject.components.collectable.CollectorComponent;
import gameObject.components.collider.Collider;
import gameObject.components.combat.CombatComponent;
import userSpace.items.IronArmor;
import userSpace.items.Sword;

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
		addComponent(new ArmorAttributeComponent(this, 100));
		addComponent(new CollectorComponent(this));
		
		CombatComponent combat = new CombatComponent(this);
		addComponent(combat);
		combat.equipArmor(new IronArmor(100, 0.5));
		combat.getArmor().setOwnerComponent(combat);
		
		combat.equipWeapon(new Sword(50, 2, 50, 100));
		combat.getWeapon().setOwner(combat);
		
		getFirstComponent(PlayerMouseController.class).setVisible(true);
		getFirstComponent(HealthAttributeComponent.class).setVisible(true);
		getFirstComponent(StaminaAttributeComponent.class).setVisible(true);
		getFirstComponent(CombatComponent.class).setVisible(true);
		getFirstComponent(ArmorAttributeComponent.class).setVisible(true);

	}
}
