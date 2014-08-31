package com.vividsolutions.jts.util;

public class StringUtil {
	
	private static final NumberFormat sFormat = NumberFormat.getFormat("0.#");
	
	public static String toString(double d) {
		return sFormat.format(d);
	}
}
