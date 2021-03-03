package snowball.embroidernox.component;

import java.util.List;

import snowball.embroidernox.enumerator.EnumComponent;
import snowball.embroidernox.interfaces.IComponent;
import snowball.embroidernox.util.Utils;

public class Eating implements IComponent {
	EnumComponent.EnumAnimation[] anims;
	EnumComponent.EnumEat[] eats;
	
	float radius;
	float hunger;
	
	int maxHunger;
	
	String[] preyes;
	
	public Eating(int maxHunger, float hunger, float radius, EnumComponent.EnumAnimation[] anims, String[] preyes, EnumComponent.EnumEat[] eats) {
		this.maxHunger = maxHunger;
		this.hunger = hunger;
		this.radius = radius;
		this.anims = anims;
		this.preyes = preyes;
		this.eats = eats;
	}
	
	@Override
	public void load(List<String> eat) {
		eat.add(Utils.value("maxHunger", maxHunger, "hungerPerHour", hunger, "eatRadius", radius));
		eat.add(Utils.value("eatAnims", anims.length));
		for (EnumComponent.EnumAnimation anim : anims) eat.add(anim.getId() + ";");
		eat.add(Utils.value("dietOptionCount", preyes.length));
		for (int i = 0; i < preyes.length; i++) eat.add(Utils.value(preyes[i], eats[i], anims[i]));
	}
}
