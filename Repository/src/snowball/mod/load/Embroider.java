package snowball.mod.load;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import resourceManagement.BlueprintRepository;
import snowball.embroidernox.entity.Entity;
import snowball.mod.Mod;
import snowball.utils.Utils;

public class Embroider {
	static void addEntities(Map<List<Entity>, Mod> map) {
		List<String> names = new ArrayList<>();
		
		for (List<Entity> entityList : map.keySet()) for(Entity entity : entityList) names.add(entity.getName());
		
		String path = Utils.getClassPath(map.get(names).getClass());
		
		BlueprintRepository.customEntities.put(names, new File(path));
	}
}
