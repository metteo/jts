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
		WKBReader r = new WKBReader();
		try {
			Geometry g = r.read(WKBReader.hexToBytes(s));
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
	String s = "0000000003000000010000002D402E08B3B320535D40498DA77E5EAAB0"
			+ "402D36D58C8EEF1C4049DF625204AF92402D5EBBBA55D1C4404A0B83621"
			+ "FAFC9402CE00D1B71758E404A4FFB15B573EB402C26279DD3BAFE404A7D"
			+ "9A06A6E32E402CB4E5B4245F5B404A9FC4113C6866402C3D477BBF93FF4"
			+ "04AE0E653868FD2402D9B15B573EAB3404B067D88C1DB0140305D0CD423"
			+ "D923404B41AF31B152F440319F71EAFEE6FB404B6CFF21B3AEEF40329EF"
			+ "09D8C6D61404B575FA22706D54032B23DC486AD2E404B3827F1B6912140"
			+ "33A91FB3FA6DF0404B3689EBA6ACA84034E46A2B1704FF404B2800D1B71"
			+ "7594036BB294DD72368404B29ECBB7F9D6F40373E75EE99A62F404B1C3B"
			+ "8A19C9D640377BEFD00713F0404AF4CCBC05D52C4037870C996B7671404"
			+ "ABC2CF52B90A84037CE10385C67E0404A8B7C4E2F37FC4037CC984E3FFE"
			+ "F4404A5875EE99A62F4037331209EDBF8C404A3E55432873BD4037820C6"
			+ "B484D77404A0306DD69D302403786EE2003AB864049CA0AC7DA1EC54038"
			+ "07AD2999567E40495A4AC6CDAF4B4037EC39CD8127B3404936628027D88"
			+ "C40376D2FA0D77B7C4049277D1FE64F55403684B923A29C784048BD06EE"
			+ "30CAA34036C6C36544FE3740488381ADEA897640368EE221C8A7A440488"
			+ "AF97679032140359B994E1A3F464048BC2C77574F724034E35104D551D7"
			+ "4048AA15336DEB9640346A746CB966BE4048B739DA16616B4033D334B51"
			+ "372A440489BCAC083126F4033521A3F4666ED4048C929563A9F384032E8"
			+ "D9E83E425B4048B7C9CD3E0BD44032DA67A52AC7544048BF8476F2A5A44"
			+ "0326496030C23FB4048FE8B652370484031A642070B8CFC40490646E08F"
			+ "217140318DF81A5870DA40492E5ACCD530494030DE67A52AC75440493CA"
			+ "B2E1693C04030B82F944241C440491B9D99029AE540302D1EEAA6D26740"
			+ "493617FC7607C440303D16A8B8F14E4049594F50A02B84402EFB60AE968"
			+ "0E040496472085B1855402E08B3B320535D40498DA77E5EAAB0";
}
