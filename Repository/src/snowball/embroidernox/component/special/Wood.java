package snowball.embroidernox.component.special;

import java.util.List;

import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.util.Vector;
import snowball.utils.Utils;

public class Wood implements IComponent {
	float bark;
	float time;
	
	Vector colour;
	
	public Wood(float cutTime, float barkFactor, Vector colour) {
		this.bark = barkFactor;
		this.colour = colour;
		this.time = cutTime;
	}
	
	@Override
	public void load(List<String> loader) {
		loader.add(Utils.value("cutTime", time, "barkFactor", bark, "colour", colour.toString()));
	}

	@Override
	public int getId() {
		return 29;
	}
}
