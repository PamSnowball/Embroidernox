package snowball.embroidernox.component.unique;

import java.util.List;

import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.util.Vector;

public class FlyTrap implements IComponent {
	Vector position;
	
	public FlyTrap(Vector flyPosition) {
		this.position = flyPosition;
	}
	
	@Override
	public void load(List<String> loader) {
		loader.add("FLY_TRAP;pos;" + position.toString());
	}

	@Override
	public int getId() {
		return 33;
	}
}
