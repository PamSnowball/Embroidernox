package snowball.embroidernox.component;

import java.util.List;

import snowball.embroidernox.interfaces.IComponent;
import snowball.embroidernox.util.Utils;

public class Growth implements IComponent {
	boolean isDynamic = false;
	
	float growth;

	int stages;
	int sub;
	
	public Growth(float averageGrowthTime, int modelStages, int subStages) {
		this.growth = averageGrowthTime;
		this.stages = modelStages;
		this.sub = subStages;
	}
	
	public Growth(float averageGrowthTime, int modelStages) {
		this.isDynamic = true;
		this.growth = averageGrowthTime;
		this.stages = modelStages;
	}
	
	public void load(List<String> grow) {
		grow.add(Utils.value("GROWTH;dynamic", isDynamic ? 1 : 0, "growthTime", growth, "modelStages", stages));
		if (isDynamic) grow.add("subStages;" + sub);
	}
}
