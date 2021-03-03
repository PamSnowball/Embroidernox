package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.interfaces.IBreed;

public class BreedEating implements IBreed {
	String classification;

	public BreedEating(String classification) {
		this.classification = classification;
	}
	
	public void requirement(List<String> breed) {
		breed.add("EATING;" + classification);
	}
}