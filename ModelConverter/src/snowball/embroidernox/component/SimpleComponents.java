package snowball.embroidernox.component;

import java.util.List;

import snowball.embroidernox.interfaces.IComponent;

public class SimpleComponents {
	public static class Sound implements IComponent {
		public void load(List<String> sound) {
			sound.add("SOUND");
		}
	}
	
	public static class Name implements IComponent {
		public void load(List<String> name) {
			name.add("NAME");
		}
	}
}
