package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.interfaces.IBreed;
import snowball.embroidernox.util.Vector;

public class BreedMaterial implements IBreed {
	Vector[] colours;
	
	public BreedMaterial(Vector[] colours) {
		this.colours = colours;
	}
	
	public void requirement(List<String> breed) {
		breed.add("MATERIAL;");
		
		for (Vector v : colours) breed.add(v.toString() + ";");
	}
}
