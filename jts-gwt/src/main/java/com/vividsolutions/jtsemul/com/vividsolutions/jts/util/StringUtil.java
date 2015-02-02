package com.vividsolutions.jts.util;

import com.vividsolutions.jts.util.NumberFormat;

public class StringUtil {
	
	private static final NumberFormat sFormat = NumberFormat.getFormat("0.#");
	
	public static String toString(double d) {
		return sFormat.format(d);
	}
}
