package com.vividsolutions.jts.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Abstracts away access to formatting classes. In JVM this implementation is
 * used. In GWT super source implementation which uses c.g.g.i.c.NumberFormat
 * 
 */
public class NumberFormat {

	private DecimalFormat mFormat;

	private NumberFormat() {

	}

	public static NumberFormat getFormat(String pattern) {
		// specify decimal separator explicitly to avoid problems in other
		// locales
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');

		DecimalFormat f = new DecimalFormat(pattern, symbols);

		NumberFormat nf = new NumberFormat();
		nf.mFormat = f;
		return nf;
	}

	public String format(Number n) {
		return mFormat.format(n);
	}
}
