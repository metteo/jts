package com.vividsolutions.jtswebapp.client;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.CoordinateSequences;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import com.vividsolutions.jts.io.WKTWriter;
import com.vividsolutions.jtswebapp.shared.Feature;
import com.vividsolutions.jtswebapp.shared.FeatureService;
import com.vividsolutions.jtswebapp.shared.FeatureServiceAsync;

public class JTSWebAppEntry implements EntryPoint, UncaughtExceptionHandler,
		ScheduledCommand {

	private static final Logger sLogger = Logger.getLogger("JTSWebAppEntry");

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

		demonstrateGeometryAndWktWriter();
	}

	private void demonstrateGeometryAndWktWriter() {
		GeometryFactory gf = new GeometryFactory();

		WKTWriter wkt2 = new WKTWriter();

		WKTWriter wkt3 = new WKTWriter(3);

		Coordinate c1 = new Coordinate(1234234.233442, 2234234.234234,
				3323424.2342342);
		Point p = gf.createPoint(c1);

		sLogger.info("Point WKT: " + wkt2.write(p));
		sLogger.info("Point WKT3: " + wkt3.write(p));

		Coordinate c2 = new Coordinate(4234234.233442, 5234223.234234,
				5323424.2342342);

		Coordinate[] c3 = new Coordinate[] { c1, c2 };

		MultiPoint mp = gf.createMultiPoint(c3);

		sLogger.info("Point WKT: " + wkt2.write(mp));
		sLogger.info("Point WKT3: " + wkt3.write(mp));

		LineString ls = gf.createLineString(c3);

		sLogger.info("Point WKT: " + wkt2.write(ls));
		sLogger.info("Point WKT3: " + wkt3.write(ls));

		MultiLineString mls = gf.createMultiLineString(new LineString[] { ls,
				ls });

		sLogger.info("Point WKT: " + wkt2.write(mls));
		sLogger.info("Point WKT3: " + wkt3.write(mls));

		Coordinate c4 = new Coordinate(6234234.233442, 8234234.234234,
				9323424.2342342);
		Coordinate[] c5 = new Coordinate[] { c1, c2, c4, c1 };

		Polygon poly = gf.createPolygon(c5);

		sLogger.info("Point WKT: " + wkt2.write(poly));
		sLogger.info("Point WKT3: " + wkt3.write(poly));
		
		MultiPolygon mpoly = gf.createMultiPolygon(new Polygon[]{poly, poly});
		
		sLogger.info("Point WKT: " + wkt2.write(mpoly));
		sLogger.info("Point WKT3: " + wkt3.write(mpoly));
		
		GeometryCollection gc = gf.createGeometryCollection(new Geometry[]{p, mp, ls, mls, poly, mpoly});
		
		sLogger.info("Point WKT: " + wkt2.write(gc));
		sLogger.info("Point WKT3: " + wkt3.write(gc));
	}
}
