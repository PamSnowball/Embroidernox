package snowball.embroidernox.component.data;

import java.util.List;

import snowball.embroidernox.component.utils.IComponent;

public class Item implements IComponent {
	float time;
	
	public Item(float decayTime) {
		this.time = decayTime;
	}
	
	@Override
	public void load(List<String> loader) {
		loader.add("ITEM;decayTime;" + time);
	}

	@Override
	public int getId() {
		return 45;
	}
}
