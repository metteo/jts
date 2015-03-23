package com.vividsolutions.jtswebapp.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Window.Location;
import com.vividsolutions.jtswebapp.client.test.TestRunner;

public class JTSWebAppEntry implements EntryPoint, UncaughtExceptionHandler {

	private static final Logger sLogger = Logger.getLogger("JTSWebAppEntry");

	public void onUncaughtException(Throwable e) {
		sLogger.log(Level.WARNING, "Uncaught exception escaped: ", e);
	}

	public void onModuleLoad() {
		sLogger.info("Module loading");

		if (GWT.isScript()) {
			GWT.setUncaughtExceptionHandler(this);

			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				
				@Override
				public void execute() {
					onLoad();
				}
			});
		} else {
			onLoad();
		}
	}

	private void onLoad() {
		String mode = Location.getParameter("mode");
		
		if(mode == null) {
			new SimpleDemo().start();
			return;
		}
		
		switch(mode) {
		case "test":
			new TestRunner().run();
			break;
		case "geojson":
			new GeoJson().check();
			break;
		default:
			new SimpleDemo().start();
			break;
		}
	}
}
