package snowball.embroidernox.component;

import java.util.ArrayList;
import java.util.List;

import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.enumerator.EnumBiome;
import snowball.embroidernox.util.Vector;
import snowball.utils.Utils;

public class Spreader implements IComponent {
	EnumBiome biome;
	
	float strenght;
	
	int distance;
	
	Vector colour;
	
	public Spreader(EnumBiome biome, Vector colour, float strenght, int distance) {
		this.biome = biome;
		this.colour = colour;
		this.strenght = strenght;
		this.distance = distance;
	}
	
	@Override
	public void load(List<String> component) {
		List<String> spread = new ArrayList<>();
		
		spread.add(Utils.value("SPREADER", biome.getId(), colour.value(), strenght, distance));
		
		Utils.append(spread, component);
	}
	
	@Override
	public int getId() {
		return 49;
	}
}
