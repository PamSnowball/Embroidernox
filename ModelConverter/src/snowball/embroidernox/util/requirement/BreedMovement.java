package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.interfaces.IBreed;

public class BreedMovement implements IBreed {
	float target;

	public BreedMovement(float target) {
		this.target = target;
	}
	
	public void requirement(List<String> breed) {
		breed.add("MOVEMENT;" + target);
	}
}
