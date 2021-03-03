package snowball.embroidernox.component;

import java.util.List;

import snowball.embroidernox.enumerator.EnumComponent.EnumPlaceSound;
import snowball.embroidernox.interfaces.IComponent;
import snowball.embroidernox.util.Utils;

public class Information implements IComponent {
	EnumPlaceSound sound;
	
	int name;
	int price;
	int dpMin;
	int range;
	
	public Information(int nameIndex, int price, int cashPerMin, int range, EnumPlaceSound sound) {
		this.name = nameIndex;
		this.price = price;
		this.dpMin = cashPerMin;
		this.range = range;
		this.sound = sound;
	}
	
	@Override
	public void load(List<String> info) {
		info.add(Utils.value("INFO;NameId", name, "DescId", name + 1, "price", price, "dpPerMin", dpMin, "range", range));
		//GameText must be deleted later
		info.add("placeSound;" + sound.name());
	}
}
