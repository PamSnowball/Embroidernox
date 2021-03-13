package snowball.utils;

public class Quantum {
	private boolean down;
	private boolean up;
	
    private final int value;
    
    public Quantum(boolean down, boolean up) {
    	this(parseQuantum(down, up));
    	this.down = down;
    	this.up = up;
	}
    
	public Quantum(int value) {
		this.value = value;
	}
	
	public static int parseQuantum(boolean down, boolean up) {
		int i = 1;

		if (!down && !up) i--;
		if (down && up) i++;
		if (down) i++;
		
		/**
		 * false + false = 0
		 * false + true = 1
		 * true + false = 2
		 * true + true = 3
		 */
		
		return i;
	}
	
	public static Quantum parseQuantum(int i) {
		boolean down = false;
		boolean up = false;
		
		if (i == 1 || i == 3) up = true;
		if (i > 1) down = true;
		
		return new Quantum(down, up);
	}
	
	public int valueOf() {
		return value;
	}
	
	public boolean getDownSpin() {
		return down;
	}
	
	public boolean getUpSpin() {
		return up;
	}
}
