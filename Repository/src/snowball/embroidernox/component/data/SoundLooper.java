package snowball.embroidernox.component.data;

import java.util.List;

import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.enumerator.EnumSound;
import snowball.utils.Utils;

public class SoundLooper implements IComponent {
	EnumSound sound;
	
	float volume;
	float range;
	
	public SoundLooper(EnumSound sound, float range, float volume) {
		this.volume = volume;
		this.range = range;
		this.sound = sound;
	}
	
	@Override
	public void load(List<String> loader) {
		loader.add(Utils.value("SOUND_LOOPER;sound", sound.getSound(), "range", range, "volume", volume));
	}
	
	@Override
	public int getId() {
		return 35;
	}
}
