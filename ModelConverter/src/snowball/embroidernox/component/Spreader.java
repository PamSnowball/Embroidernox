package snowball.embroidernox.component;

import java.util.List;

import snowball.embroidernox.enumerator.EnumComponent.EnumBiome;
import snowball.embroidernox.interfaces.IComponent;
import snowball.embroidernox.util.Utils;
import snowball.embroidernox.util.Vector;

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
	public void load(List<String> spread) {
		spread.add(Utils.value("SPREADER", biome.getId()));
		spread.add(Utils.value(colour.getX(), colour.getY(), colour.getZ(), strenght, distance));	
	}
}
