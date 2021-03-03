package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.interfaces.IBreed;

public class BreedFruitFall implements IBreed {
	float target;

	public BreedFruitFall(float target) {
		this.target = target;
	}
	
	public void requirement(List<String> breed) {
		breed.add("FRUIT_FALL;" + target);
	}
}
