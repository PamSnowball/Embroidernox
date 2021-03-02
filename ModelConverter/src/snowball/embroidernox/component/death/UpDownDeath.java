package snowball.embroidernox.component.death;

import java.util.List;

import snowball.embroidernox.util.Utils;

public class UpDownDeath implements IDeath {
	ParticleDeath particle;
	
	float speed;
	
	public UpDownDeath(float speed, ParticleDeath particle) {
		this.speed = speed;
		this.particle = particle;
	}
		
	public void death(List<String> death) {
		death.add(Utils.value("UP_DOWN_DEATH", "speed", speed));
		particle.deathPrint(death);
	}
}
