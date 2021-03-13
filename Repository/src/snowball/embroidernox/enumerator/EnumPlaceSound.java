package snowball.embroidernox.enumerator;

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
