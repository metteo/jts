package com.vividsolutions.jtsexample.client;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.CoordinateSequences;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import com.vividsolutions.jtsexample.shared.Feature;
import com.vividsolutions.jtsexample.shared.FeatureService;
import com.vividsolutions.jtsexample.shared.FeatureServiceAsync;

public class JTSExampleEntry implements EntryPoint, UncaughtExceptionHandler,
		ScheduledCommand {

	private static final Logger sLogger = Logger.getLogger("JTSExampleEntry");
	
	public void onUncaughtException(Throwable e) {
		sLogger.log(Level.WARNING, "Uncaught exception escaped: ", e);
	}

	public void onModuleLoad() {
		sLogger.info("Module loading");

		if (GWT.isScript()) {
			GWT.setUncaughtExceptionHandler(this);

			Scheduler.get().scheduleDeferred(this);
		} else {
			onLoad();
		}
	}
	
	public void execute() {
		onLoad();
	}

	private void onLoad() {
		FeatureServiceAsync features = GWT.create(FeatureService.class);

		Feature feature = new Feature();
		feature.fid = "feature.1";
		feature.geometry = new Coordinate(1, 2);
		feature.properties = new HashMap<String, String>();
		feature.properties.put("name", "JTS!");

		sLogger.info("Original feature: " + feature);

		features.echo(feature, new AsyncCallback<Feature>() {

			public void onSuccess(Feature result) {
				sLogger.info("Echoed feature: " + result);
			}

			public void onFailure(Throwable caught) {
				sLogger.log(Level.WARNING, "Unable to make GWT-RPC request",
						caught);
			}
		});

		CoordinateSequence cs = new CoordinateArraySequence(new Coordinate[] {
				new Coordinate(1, 2, 3), new Coordinate(4, 5) });

		sLogger.info("CoordinateSequence: " + cs);
		sLogger.info("Copied CS: " + CoordinateSequences.copy(cs));
	}
}
