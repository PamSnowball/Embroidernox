package snowball.embroidernox.enumerator;

import snowball.embroidernox.util.Vector;

public enum EnumColours {
	RED(new Vector(0.945F, 0.569F, 0.569F)),
	RUBY_RED(new Vector(162.0F, 79.0F, 79.0F, 255.0F)),
	YELLOW(new Vector(0.835F, 0.827F, 0.502F)),
	ORANGE(new Vector(0.882F, 0.706F, 0.494F)),
	DARK_ORANGE(new Vector(0.678F, 0.404F, 0.333F)),
	LIGHT_BLUE(new Vector(0.467F, 0.729F, 0.769F)),
	DARK_BLUE(new Vector(0.314F, 0.329F, 0.545F)),
	PINK(new Vector(0.933F, 0.631F, 0.823F)),
	LIGHT_PINK(new Vector(255.0F, 198.0F, 241.0F, 255.0F)),
	PURPLE(new Vector(0.741F, 0.537F, 0.835F)),
	DARK_PURPLE(new Vector(76.0F, 55.0F, 88.0F, 255.0F)),
	LILAC(new Vector(204.0F, 194.0F, 247.0F, 255.0F)),
	CYAN(new Vector(0.533F, 0.89F, 0.792F)),
	BEIGE(new Vector(209.0F, 192.0F, 162.0F, 255.0F)),
	MUD(new Vector(105.0F, 102.0F, 83.0F, 255.0F)),
	LIGHT_BROWN(new Vector(0.612F, 0.506F, 0.38F)),
	BROWN(new Vector(0.51F, 0.4F, 0.306F)),
	DARK_BROWN(new Vector(78.0F, 61.0F, 61.0F, 255.0F)),
	YELLOW_GREEN(new Vector(0.655F, 0.733F, 0.439F)),
	DARK_GREEN(new Vector(0.282F, 0.435F, 0.266F)),
	BLUE_GREEN(new Vector(0.345F, 0.576F, 0.439F)),
	BRIGHT_GREEN(new Vector(149.0F, 245.0F, 161.0F, 255.0F)),
	LIGHT_GREEN(new Vector(0.553F, 0.89F, 0.553F)),
	GREEN(new Vector(0.365F, 0.588F, 0.365F)),
	PALE_GREEN(new Vector(0.792F, 0.871F, 0.522F)),
	MUD_GREEN(new Vector(0.478F, 0.522F, 0.325F)),
	GOLD(new Vector(0.702F, 0.639F, 0.31F)),
	WHITE(new Vector(0.9F, 0.9F, 0.9F)),
	GREY(new Vector(0.5F, 0.5F, 0.5F)),
	LIGHT_GREY(new Vector(0.75F, 0.75F, 0.75F)),
	DARK_GREY(new Vector(0.25F, 0.25F, 0.25F)),
	BLACK(new Vector(0.15F, 0.15F, 0.15F)),
	NEON_RED(new Vector(255.0F, 102.0F, 102.0F, 255.0F)),
	NEON_ORANGE(new Vector(255.0F, 202.0F, 91.0F, 255.0F)),
	NEON_YELLOW(new Vector(255.0F, 245.0F, 102.0F, 255.0F)),
	NEON_GREEN(new Vector(194.0F, 255.0F, 102.0F, 255.0F)),
	NEON_BLUE(new Vector(102.0F, 255.0F, 235.0F, 255.0F)),
	NEON_PURPLE(new Vector(125.0F, 85.0F, 255.0F, 255.0F)),
	NEON_PINK(new Vector(255.0F, 102.0F, 235.0F, 255.0F));
	
	private final Vector colour;

	EnumColours(Vector colour) {
		this.colour = colour;
	}
	
	public Vector getColour() {
		return colour;
	}
}
