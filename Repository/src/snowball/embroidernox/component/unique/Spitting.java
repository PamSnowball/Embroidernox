package snowball.embroidernox.component.unique;

import java.util.List;

import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.util.Vector;
import snowball.utils.Utils;

public class Spitting implements IComponent {
	Vector position;
	
	public Spitting(Vector spittingPosition) {
		this.position = spittingPosition;
	}
	
	@Override
	public void load(List<String> loader) {
		loader.add(Utils.value("SPITTING;spitPosition", position));
	}

	@Override
	public int getId() {
		return 4;
	}
}
