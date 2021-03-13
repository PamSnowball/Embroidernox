package snowball.embroidernox.component.food;

import java.util.ArrayList;
import java.util.List;

import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.util.component.IDeath;
import snowball.utils.Utils;

public class Healer implements IComponent {
	IDeath death;
	
	public Healer(IDeath death) {
		this.death = death;
	}
	
	public void load(List<String> component) {
		if (death != null) {
			List<String> heal = new ArrayList<>();
		
			heal.add("HEALER;");
			death.death(heal);
		
			Utils.append(heal, component);
		}
	}

	@Override
	public int getId() {
		return 40;
	}
}
