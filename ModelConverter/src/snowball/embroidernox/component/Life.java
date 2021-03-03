package snowball.embroidernox.component;

import java.util.List;

import snowball.embroidernox.interfaces.IComponent;
import snowball.embroidernox.util.component.Breed;

public class Life implements IComponent {
	float averagePopulation;
	float averageLife;

	float[] popFactors;
	
	public Life(float averagePopulation, float averageLifespan, float[] populationFactors, Breed breed) {
		this.averagePopulation = averagePopulation;
		this.averageLife = averageLifespan;
		this.popFactors = populationFactors;
	}
	
	public void load(List<String> life) {
	
	}
}
