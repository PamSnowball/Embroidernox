package snowball.embroidernox.component.death;

import java.util.List;

import snowball.embroidernox.interfaces.IComponent;

public class FloatDeath implements IComponent {
	float deathRot;
	
	public FloatDeath(float deathRotation) {
		this.deathRot = deathRotation;
	}
		
	public void load(List<String> death) {
		death.add("FLOAT_DEATH;deathRot;" + deathRot);
	}
}
