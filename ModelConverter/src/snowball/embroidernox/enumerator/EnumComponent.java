package snowball.embroidernox.enumerator;

public class EnumComponent {
	public enum EnumPlaceSound { 
		BEAR("bearPlace"),
		GRASS("grassPlace"),
		THUD("thud"),
		GOAT("goat0"),
		SPLASH("splash");

		private String sound;
		
		EnumPlaceSound(String file) {
			this.sound = file;
		}
		
		public String getSound() {
			return this.sound;
		}
	}

	public enum EnumBiome { 
		GRASSLAND(0),
		FOREST(1),
		RIVER_BED(2),
		DESERT(3),
		SNOW(4),
		JUNGLE(5),
		SWAMP(6),
		LUSH(7),
		WOODLAND(8),
		TROPICAL(9);
		
		private int id;
		
		EnumBiome(int id) {
			this.id = id;
		}
		
		public int getId() {
			return this.id;
		}
	}

	public enum EnumAnimation { 
		COMMON(0),
		THROWING(1),
		DIGGING(2),
		DIVING(3),
		INSTANT(4);
		
		private int id;
		
		EnumAnimation(int id) {
			this.id = id;
		}
		
		public int getId() {
			return this.id;
		}
	}

	public enum EnumEat { WHOLE, FRUIT, UPROOT, SAMPLE, TO_SHARE, HONEY, ROOT_VEG }
}
