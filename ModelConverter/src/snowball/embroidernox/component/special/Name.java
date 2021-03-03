package snowball.embroidernox.component.special;

import java.util.List;

import snowball.embroidernox.interfaces.IComponent;

public class Name implements IComponent {
	public void load(List<String> name) {
		name.add("NAME");
	}
}