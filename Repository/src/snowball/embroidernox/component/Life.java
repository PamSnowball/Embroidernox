package snowball.embroidernox.component;

import java.util.ArrayList;
import java.util.List;

import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.util.component.Breed;
import snowball.embroidernox.util.component.IDeath;
import snowball.embroidernox.util.requirement.IEnvironment;
import snowball.utils.Utils;

public class Life implements IComponent {
	Breed breed;
	
	float population;
	float lifespan;
	
	float[] factors;
	
	IDeath death;
	IEnvironment[] enviros;
	
	int points = 0;
	
	public Life(float averagePopulation, float averageLifespan, float[] populationFactors, Breed breed, IDeath death, 
			IEnvironment[] lifeRequirements) {
		this.population = averagePopulation;
		this.lifespan = averageLifespan;
		this.factors = populationFactors;
		this.breed = breed;
		this.death = death;
		this.enviros = lifeRequirements;
	}
	
	public Life(float averagePopulation, float averageLifespan, float[] populationFactors, Breed breed, IDeath death, 
			IEnvironment[] lifeRequirements, int defencePoints) {
		this.population = averagePopulation;
		this.lifespan = averageLifespan;
		this.factors = populationFactors;
		this.breed = breed;
		this.death = death;
		this.enviros = lifeRequirements;
		this.points = defencePoints;
	}

	public void load(List<String> component) {
		List<String> life = new ArrayList<>();
		
		life.add(Utils.value("LIFE;averagePopulation", population, "averageLife", lifespan, "popFactors", factors.length));
		if (factors.length != 0) for (float factor : factors) life.add(factor + ";");
		breed.breed(life);
		death.death(life);
		life.add("enviroReqCount;" + enviros.length + ';');
		for (IEnvironment enviro : enviros) enviro.requirement(life);
		if (points != 0) {
			life.add("defencePoints;" + points);
		}
		
		Utils.append(life, component);
	}
	
	@Override
	public int getId() {
		return 43;
	}
}
