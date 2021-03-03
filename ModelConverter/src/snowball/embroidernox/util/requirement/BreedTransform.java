package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.interfaces.IBreed;

public class BreedTransform implements IBreed {
	float size;

	public BreedTransform(float targetSize) {
		this.size = targetSize;
	}
	
	public void requirement(List<String> breed) {
		breed.add("TRANSFORM;" + size);
	}
}
