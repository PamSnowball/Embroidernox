package snowball.embroidernox.component.special;

import java.util.List;

import snowball.embroidernox.interfaces.IComponent;
import snowball.embroidernox.util.Utils;

public class Fruiter implements IComponent {
	float time = 5.0F;
	
	int count;
	int index;
	
	public Fruiter(int fruitModelIndex, int stageCount) {
		this.index = fruitModelIndex;
		this.count = stageCount;
	}
	
	public Fruiter(int fruitModelIndex, int stageCount, float fruitTime) {
		this.index = fruitModelIndex;
		this.count = stageCount;
		this.time = fruitTime;
	}
	
	public void load(List<String> fruit) {
		fruit.add(Utils.value("FRUITER;modelStage", index, "stageCount", count));
		if (time != 5.0F) fruit.add("time;" + time);
	}
}
