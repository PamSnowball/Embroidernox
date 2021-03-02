package snowball.embroidernox.component.death;

import java.util.Arrays;
import java.util.List;

import snowball.embroidernox.util.Utils;
import snowball.embroidernox.util.Vector;

public class ParticleDeath implements IDeath {
	private static String i = "isColour";
	
	boolean isColour = false;
	boolean isAdditive;
	boolean isCircle;
	boolean hasDir;
	
	float[] particleArray;
	float radius = 0.0F;
	
	int atlas;
	
	Object[] hasRot;
	
	Vector v = new Vector(0.0F, 0.0F, 0.0F);
	Vector colour;
	
	public ParticleDeath(float[] particle, int atlas, Object[] hasRotation) {
		this.atlas = atlas;
		if (particle.length == 11) this.particleArray = particle;
		else if (particle.length == 15)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public ParticleDeath(float[] particle, Vector colour, boolean isAdditive, Object[] hasRotation) {
		this.isColour = true;
		this.isAdditive = isAdditive;
		this.colour = colour;
		if (particle.length == 14) this.particleArray = particle;
		else if (particle.length == 18)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public ParticleDeath(float radius, float[] particle, int atlas, Object[] hasRotation) {
		this.radius = radius;
		this.atlas = atlas;
		if (particle.length == 11) this.particleArray = particle;
		else if (particle.length == 15)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public ParticleDeath(float radius, float[] particle, Vector colour, boolean isAdditive, Object[] hasRotation) {
		this.isColour = true;
		this.isAdditive = isAdditive;
		this.colour = colour;
		this.radius = radius;
		if (particle.length == 14) this.particleArray = particle;
		else if (particle.length == 18)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public ParticleDeath(float radius, Vector coordinates, float[] particle, int atlas, Object[] hasRotation) {
		this.isCircle = true;
		this.radius = radius;
		this.v = coordinates;
		this.atlas = atlas;
		if (particle.length == 11) this.particleArray = particle;
		else if (particle.length == 15)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public ParticleDeath(boolean isCircle, float radius, Vector coordinates, float[] particle, Vector colour, boolean isAdditive, 
			Object[] hasRotation) {
		this.isColour = true;
		this.isAdditive = isAdditive;
		this.isCircle = isCircle;
		this.colour = colour;
		this.radius = radius;
		this.v = coordinates;
		if (particle.length == 14) this.particleArray = particle;
		else if (particle.length == 18)  {
			this.hasDir = true;
			this.particleArray = particle;
		}
		
		if (hasRotation.length > 1)  {
			hasRotation[2] = true;
			this.hasRot = hasRotation;
		}
	}
	
	public void death(List<String> death) {
	 	death.add("PARTICLE_DEATH;");
		deathPrint(death);
	}
	
	public void deathPrint(List<String> death) {
		if (Utils.check(0.0F, radius, v.getX(), v.getY(), v.getZ())) death.add(Utils.value(i, isColour ? 1 : 0, "spawn;0"));
		else if (Utils.check(0.0F, v.getX(), v.getY(), v.getZ())) death.add(Utils.value(i, isColour ? 1 : 0, "spawn;1"));
		else death.add(Utils.value(i, isColour ? 1 : 0, "spawn", isCircle ? 3 : 2, radius, v.toString()));
		death.add(Utils.value("pps", particleArray[0], "speed", particleArray[1], "gravity", particleArray[2], "life", particleArray[3]));
		death.add(Utils.value("scale", particleArray[4]));
		if (isColour) {
			death.add(Utils.value("colour", colour.toString(), "additive", isAdditive ? 1 : 0));
			death.add(Utils.value("alpha", particleArray[5], "fadeIn", particleArray[6], "fadeOut", particleArray[7]));
		} else death.add(Utils.value("atlas", atlas));
		loadArray(death);
	}
	
	private void loadArray(List<String> death) {
		int j = 6;
		
		death.add(Utils.value("hasDir", hasDir ? 1 : 0));
		if (hasDir) {
			if (isColour) j = 9;
			death.add(Utils.value("direction", particleArray[j], particleArray[j+1], particleArray[j+2], "deviation", particleArray[j+3]));
			j += 4;
		}
		
		death.add(Utils.value("offset", particleArray[j], particleArray[j+1], particleArray[j+2]));
		death.add(Utils.value("lifeError", particleArray[j+3], "scaleError", particleArray[j+4], "speedError", particleArray[j+5]));
		death.add(Utils.value("hasRot", (boolean) hasRot[0] ? 1 : 0));
		if (hasRot[1] != null) death.add(Utils.value("hasXRot;1;rotation", hasRot[1]));
		else death.add("hasXRot;0");
	}
	
	public static ParticleDeath loadParticleDeath(float[] floatArray, boolean[] boolArray, Vector[] vectorArray, Object[] objArray, int atlas) {
		if (vectorArray == null && boolArray == null && (floatArray.length == 11 || floatArray.length == 15))  {
			return new ParticleDeath(floatArray, atlas, objArray);
		} else if (vectorArray != null && boolArray != null && atlas == -1) {
			return new ParticleDeath(floatArray, vectorArray[0], boolArray[0], objArray);
		} else if (vectorArray == null && boolArray == null && (floatArray.length == 12 || floatArray.length == 16)) { 
			return new ParticleDeath(floatArray[0], Arrays.copyOfRange(floatArray, 1, floatArray.length), atlas, objArray);
		}  else if (vectorArray != null && boolArray != null && (floatArray.length == 12 || floatArray.length == 16) && atlas == -1) { 
			return new ParticleDeath(floatArray[0], Arrays.copyOfRange(floatArray, 1, floatArray.length), 
					vectorArray[0], boolArray[0], objArray);
		} else if (vectorArray != null && boolArray == null && (floatArray.length == 12 || floatArray.length == 16)) {
			return new ParticleDeath(floatArray[0], vectorArray[0], Arrays.copyOfRange(floatArray, 1, floatArray.length),
					atlas, objArray);
		} else if (vectorArray != null && boolArray != null) {
			return new ParticleDeath(boolArray[0], floatArray[0], vectorArray[0], 
					Arrays.copyOfRange(floatArray, 1, floatArray.length), vectorArray[1], boolArray[1], objArray);
		}
		return null;
	}
}