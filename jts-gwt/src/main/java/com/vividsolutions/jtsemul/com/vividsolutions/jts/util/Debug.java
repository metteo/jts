package com.vividsolutions.jts.util;

import java.util.logging.Logger;

import com.vividsolutions.jts.geom.Coordinate;

public class Debug {
	
	private static final Logger sLogger = Logger.getLogger("Debug");
	
	public static String toLine(Coordinate p0, Coordinate p1, Coordinate p2) {
		return p0 + " -- " + p1 + " -- " + p2;
	}
	
	public static void println(String ln) {
		sLogger.fine(ln);
	}
	
	public static boolean isDebugging() {
		return false;
	}
}
