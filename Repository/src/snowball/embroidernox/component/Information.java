package snowball.embroidernox.component;

import java.util.ArrayList;
import java.util.List;

import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.enumerator.EnumPlaceSound;
import snowball.utils.Utils;

public class Information implements IComponent {
	EnumPlaceSound sound;
	
	int price;
	int dpMin;
	int range;

	String name;
	String desc;
	
	public Information(String name, String description, int price, int cashPerMin, int range, EnumPlaceSound sound) {
		this.name = name;
		this.desc = description;
		this.price = price;
		this.dpMin = cashPerMin;
		this.range = range;
		this.sound = sound;
	}
	
	@Override
	public void load(List<String> component) {
		List<String> info = new ArrayList<>();
		
		if (!desc.endsWith(".")) desc += '.';
		if (name.endsWith(".")) name = name.substring(0, name.length() - 1);
		
		info.add(Utils.value("CUSTOM_INFO;Name", name, "Desc", desc, "price", price, "dpPerMin", dpMin, "range", range));
		info.add("placeSound;" + sound.name());
		
		Utils.append(info, component);
	}
	
	@Override
	public int getId() {
		return 46;
	}
}
