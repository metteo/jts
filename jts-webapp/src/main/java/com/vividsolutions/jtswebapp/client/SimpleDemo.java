package com.vividsolutions.jtswebapp.client;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
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
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKBWriter;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import com.vividsolutions.jtswebapp.shared.Feature;
import com.vividsolutions.jtswebapp.shared.FeatureService;
import com.vividsolutions.jtswebapp.shared.FeatureServiceAsync;

public class SimpleDemo {

	private static final Logger sLogger = Logger.getLogger("SimpleDemo");

	JavaScriptObject mMap;

	public void start() {
		mMap = initMap();
		showPoland();
		testGwtRpcAndWktAndWkb();
	}

	private static native JavaScriptObject initMap() /*-{
		var map = new $wnd.ol.Map({
			target : 'map',
			layers : [ new $wnd.ol.layer.Tile({
				source : new $wnd.ol.source.OSM()
			}) ],
			view : new $wnd.ol.View({
				center : [ 0, 0 ],
				zoom : 2
			})
		});

		return map;
	}-*/;

	private void testGwtRpcAndWktAndWkb() {
		FeatureServiceAsync features = GWT.create(FeatureService.class);

		CoordinateSequence cs = new CoordinateArraySequence(new Coordinate[] {
				new Coordinate(1, 2, 3), new Coordinate(4, 5) });

		sLogger.info("CoordinateSequence: " + cs);
		sLogger.info("Copied CS: " + CoordinateSequences.copy(cs));

		final Geometry g = demonstrateGeometryAndWktWriter();
		demonstrateWkb();

		Feature feature = new Feature();
		feature.fid = "feature.1";
		feature.geometry = g;
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
	}

	private Geometry demonstrateGeometryAndWktWriter() {
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

		MultiPolygon mpoly = gf
				.createMultiPolygon(new Polygon[] { poly, poly });

		sLogger.info("Point WKT: " + wkt2.write(mpoly));
		sLogger.info("Point WKT3: " + wkt3.write(mpoly));

		GeometryCollection gc = gf.createGeometryCollection(new Geometry[] { p,
				mp, ls, mls, poly, mpoly });

		sLogger.info("Point WKT: " + wkt2.write(gc));
		sLogger.info("Point WKT3: " + wkt3.write(gc));

		return gc;
	}

	private void demonstrateWkb() {
		GeometryFactory gf = new GeometryFactory();

		WKBReader wkbReader = new WKBReader(gf);
		WKBWriter wkbWriter = new WKBWriter();

		// geometry collection from above
		String hexEncodedWkb = "00000000070000000600000000014132D53A3BC2DADC414"
				+ "10BBD1DFB613500000000040000000200000000014132D53A3BC2DADC414"
				+ "10BBD1DFB61350000000001415026FE8EF0B6B74153F78BCEFDB09A00000"
				+ "00002000000024132D53A3BC2DADC41410BBD1DFB6135415026FE8EF0B6B"
				+ "74153F78BCEFDB09A0000000005000000020000000002000000024132D53"
				+ "A3BC2DADC41410BBD1DFB6135415026FE8EF0B6B74153F78BCEFDB09A000"
				+ "0000002000000024132D53A3BC2DADC41410BBD1DFB6135415026FE8EF0B"
				+ "6B74153F78BCEFDB09A000000000300000001000000044132D53A3BC2DAD"
				+ "C41410BBD1DFB6135415026FE8EF0B6B74153F78BCEFDB09A4157C81E8EF"
				+ "0B6B7415F693E8EFDB09A4132D53A3BC2DADC41410BBD1DFB61350000000"
				+ "00600000002000000000300000001000000044132D53A3BC2DADC41410BB"
				+ "D1DFB6135415026FE8EF0B6B74153F78BCEFDB09A4157C81E8EF0B6B7415"
				+ "F693E8EFDB09A4132D53A3BC2DADC41410BBD1DFB6135000000000300000"
				+ "001000000044132D53A3BC2DADC41410BBD1DFB6135415026FE8EF0B6B74"
				+ "153F78BCEFDB09A4157C81E8EF0B6B7415F693E8EFDB09A4132D53A3BC2D"
				+ "ADC41410BBD1DFB6135";

		try {
			Geometry g = wkbReader.read(WKBReader.hexToBytes(hexEncodedWkb));

			sLogger.info("Geom from WKB: " + g);

			byte[] freshWkb = wkbWriter.write(g);
			String freshWkbHex = WKBWriter.toHex(freshWkb);

			sLogger.warning("Hexes are equal?   "
					+ hexEncodedWkb.equals(freshWkbHex));
		} catch (ParseException e) {
			sLogger.log(Level.WARNING, "Unable to parse hex wkb", e);
		}
	}

	private void showPoland() {
		WKTReader r = new WKTReader();
		try {
			Geometry g = r.read(s);
			g.setSRID(4326);

			Geometry g2 = g.buffer(1);
			Geometry g3 = g2.difference(g);

			JavaScriptObject f1 = parseWKT(g.toString());
			JavaScriptObject f2 = parseWKT(g3.toString());
			JsArray<JavaScriptObject> fs = JsArray.createArray().cast();
			fs.push(f1);
			fs.push(f2);

			addFeatures(mMap, fs);
		} catch (ParseException e) {
			sLogger.log(Level.WARNING, "Unable to parse wkb", e);
		}
	}

	private static native JavaScriptObject parseWKT(String wkt) /*-{
		var format = new $wnd.ol.format.WKT();
		var feature = format.readFeature(wkt);
		feature.getGeometry().transform('EPSG:4326', 'EPSG:3857');
		return feature;
	}-*/;

	private static native void addFeatures(JavaScriptObject map,
			JavaScriptObject features)/*-{
		var vector = new $wnd.ol.layer.Vector({
			source : new $wnd.ol.source.Vector({
				features : features
			})
		});

		map.addLayer(vector);
	}-*/;

	// poland
	String s = "POLYGON ((15.016996 51.106674, 14.607098 51.745188, 14.685026 "
			+ "52.089947, 14.4376 52.62485, 14.074521 52.981263, 14.353315 "
			+ "53.248171, 14.119686 53.757029, 14.8029 54.050706, 16.363477 "
			+ "54.513159, 17.622832 54.851536, 18.620859 54.682606, 18.696255 "
			+ "54.438719, 19.66064 54.426084, 20.892245 54.312525, 22.731099 "
			+ "54.327537, 23.243987 54.220567, 23.484128 53.912498, 23.527536 "
			+ "53.470122, 23.804935 53.089731, 23.799199 52.691099, 23.199494 "
			+ "52.486977, 23.508002 52.023647, 23.527071 51.578454, 24.029986 "
			+ "50.705407, 23.922757 50.424881, 23.426508 50.308506, 22.51845 "
			+ "49.476774, 22.776419 49.027395, 22.558138 49.085738, 21.607808 "
			+ "49.470107, 20.887955 49.328772, 20.415839 49.431453, 19.825023 "
			+ "49.217125, 19.320713 49.571574, 18.909575 49.435846, 18.853144 "
			+ "49.49623, 18.392914 49.988629, 17.649445 50.049038, 17.554567 "
			+ "50.362146, 16.868769 50.473974, 16.719476 50.215747, 16.176253 "
			+ "50.422607, 16.238627 50.697733, 15.490972 50.78473, 15.016996 "
			+ "51.106674))";
}
