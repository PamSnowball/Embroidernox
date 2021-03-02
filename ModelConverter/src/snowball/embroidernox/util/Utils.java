package snowball.embroidernox.util;

import java.util.Arrays;

public class Utils {
	public static boolean check(Object o, Object... os) {
		for (Object obj : os) if (obj != o) return true;
		return false;
	}

	public static String value(Object... os) {
		String[] strings = Arrays.stream(os).toArray(String[]::new);
		return String.join(";", strings) + ';';
	}

	public static String value(Object o) {
		return String.valueOf(o) + ';';
	}

	public static String value(String label, Object o) {
		return label + ';' + o.toString() + ';';
	}
}
