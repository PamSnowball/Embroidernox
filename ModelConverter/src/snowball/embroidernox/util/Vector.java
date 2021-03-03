package snowball.embroidernox.util;

public class Vector {
	private float x;
	private float y;
	private float z;

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector(float x, float y, float z, float w) {
		this.x = x / w;
		this.y = y / w;
		this.z = z / w;
	}
	
	public String toString() {
		return Utils.value(x, y) + String.valueOf(z); 
	}
	
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public float getZ() { return this.z; }
}
