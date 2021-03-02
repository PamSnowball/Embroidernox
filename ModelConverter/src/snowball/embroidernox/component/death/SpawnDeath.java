package snowball.embroidernox.component.death;

import java.util.List;

import snowball.embroidernox.util.Utils;

public class SpawnDeath implements IDeath {
	boolean growth;
	int min;
	int max;
	int id;
		
	public SpawnDeath(int entityId, int minCount, int maxCount, boolean growth) {
		this.growth = growth;
		this.min = minCount;
		this.max = maxCount;
		this.id = entityId;
	}
		
	public void death(List<String> death) {
		death.add(Utils.value(id, min, max, growth ? 1 : 0));
	}
}