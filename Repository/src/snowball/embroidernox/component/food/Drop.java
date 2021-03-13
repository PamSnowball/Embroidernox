package snowball.embroidernox.component.food;

import java.util.List;

import resourceManagement.BlueprintRepository;
import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.entity.Entity;

public class Drop implements IComponent {
	Entity entity;
	
	int index;
	
	public Drop(int droppedItemIndex, Entity entity) {
		this.index = droppedItemIndex;
		this.entity = entity;
	}
	
	@Override
	public void load(List<String> loader) {
		boolean isNonLiving = BlueprintRepository.getBlueprint(index).getClassification().getKey().startsWith("e");
		boolean isCustomNonLiving = Entity.getClassification(index).startsWith("e");
		
		if (isNonLiving || isCustomNonLiving) loader.add("DROP;itemId;" + index);
	}

	@Override
	public int getId() {
		return 7;
	}
}
