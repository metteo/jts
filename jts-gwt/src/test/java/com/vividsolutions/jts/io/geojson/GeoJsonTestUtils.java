package com.vividsolutions.jts.io.geojson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class GeoJsonTestUtils {
	public static String readGeoJsonFile(String name) throws IOException {
		InputStream is = GeoJsonReaderTest.class.getResourceAsStream(name
				+ ".geo.json");
		Reader r = new InputStreamReader(is, Charset.forName("UTF-8"));

		StringBuilder sb = new StringBuilder();

		char[] cbuf = new char[1024];
		int len = -1;
		while ((len = r.read(cbuf)) != -1) {
			sb.append(cbuf, 0, len);
		}

		return sb.toString();
	}
}
