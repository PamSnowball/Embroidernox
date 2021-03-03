package snowball.embroidernox.component;

import java.util.List;

import snowball.embroidernox.entity.Entity;
import snowball.embroidernox.interfaces.IComponent;
import snowball.embroidernox.util.Utils;
import snowball.embroidernox.util.Vector;

public class Material implements IComponent {
	boolean hasSecond;
	
	int count;
	
	Vector[] colours;
	
	public Material(boolean hasSecondNatural, int colourCount, Vector[] colours) {
		this.hasSecond = hasSecondNatural;
		this.count = colourCount;
		this.colours = colours;
	}
	
	@Override
	public void load(List<String> colour) {
		colour.add(Utils.value("MATERIAL;second" + (hasSecond ? 1 : 0), "count" + count));
		for (int i = 0; i < count; i++) colour.add(Utils.value(Entity.check(colours[i]), colours[i].toString()));
	}
}
