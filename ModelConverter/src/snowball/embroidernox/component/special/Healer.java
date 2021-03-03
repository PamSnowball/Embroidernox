package snowball.embroidernox.component.special;

import java.util.List;

import snowball.embroidernox.interfaces.IComponent;
import snowball.embroidernox.interfaces.IDeath;

public class Healer implements IComponent {
	IDeath death;
	
	public Healer(IDeath death) {
		this.death = death;
	}
	
	public void load(List<String> heal) {
		heal.add("HEALER;");
		death.death(heal);
	}
}
