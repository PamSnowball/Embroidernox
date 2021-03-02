package snowball.embroidernox.component.death;

import java.util.List;

import snowball.embroidernox.util.Utils;

public class FallDeath implements IDeath {
	ParticleDeath particle;
	
	boolean hasParticleEffect;
	boolean useEntityColour;
	
	float explodeTime;
	float fallTime;
	float totalTime;
	float angle;
	
	int[] modelCount;
			
	public FallDeath(float fallTime, float totalTime, float fallAngle) {
		this.fallTime = fallTime;
		this.totalTime = totalTime;
		this.angle = fallAngle;
	}
	public FallDeath(float fallTime, float totalTime, float fallAngle, float explodeTime, 
			ParticleDeath particle, boolean useEntityColour) {
		this.fallTime = fallTime;
		this.totalTime = totalTime;
		this.angle = fallAngle;
		this.explodeTime = explodeTime;
		this.useEntityColour = useEntityColour;
		this.particle = particle;
	}
		
	public void death(List<String> death) {
		death.add(Utils.value("FALL_DEATH", "fallTime", fallTime, "totalTime", totalTime, "fallAngle", angle));
		if (hasParticleEffect) {
			death.add(Utils.value("explodeTime", explodeTime, "useMaterial", useEntityColour ? 1 : 0));
			particle.deathPrint(death);
			death.add(Utils.value("Stages", modelCount.length));
			for (int model : modelCount) death.add(Utils.value(model));
		}
	}
}