package snowball.embroidernox.component.unique;

import java.util.List;

import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.util.Vector;

public class TreeSwing implements IComponent {
	Vector position;
	
	public TreeSwing(Vector handPosition) {
		this.position = handPosition;
	}
	
	@Override
	public void load(List<String> loader) {
		loader.add("TREE_SWING;handPos;" + position.toString());
	}

	@Override
	public int getId() {
		return 15;
	}
}
