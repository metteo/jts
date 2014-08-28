package com.vividsolutions.jtsexample.client;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jtsexample.shared.Feature;
import com.vividsolutions.jtsexample.shared.FeatureService;
import com.vividsolutions.jtsexample.shared.FeatureServiceAsync;

public class JTSExampleEntry implements EntryPoint {

	private static final Logger sLogger = Logger.getLogger("JTSExampleEntry");

	public void onModuleLoad() {
		sLogger.info("Module loading");
		
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
				sLogger.log(Level.WARNING, "Unable to make GWT-RPC request", caught);
			}
		});
	}
}
