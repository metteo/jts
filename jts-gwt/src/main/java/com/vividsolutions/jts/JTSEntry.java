package com.vividsolutions.jts;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;

public class JTSEntry implements EntryPoint {
	
	private static final Logger sLogger = Logger.getLogger("JTSEntry");

	public void onModuleLoad() {
		sLogger.info("JTS " + JTSVersion.CURRENT_VERSION);
	}

}
