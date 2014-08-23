package test.jts;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class TestFiles {

	public static final String GML_DIR = "gml/";
	public static final String WKT_DIR = "wkt/";
	public static final String XML_DIR = "xml/";

	public static Reader getReader(String path) {
		InputStream is = TestFiles.class.getClassLoader().getResourceAsStream(path);
		BufferedInputStream bis = new BufferedInputStream(is);
		InputStreamReader isr = new InputStreamReader(bis);
		return isr;
	}
	
	public static File getFile(String path) {
		URL url = TestFiles.class.getClassLoader().getResource(path);

		try {
			URI uri = url.toURI();
			return new File(uri);
		} catch (URISyntaxException e) {
			throw new RuntimeException("Unable to convert URL to URI", e);
		}
	}
	
	public static String getFilePath(String path){
		return getFile(path).getAbsolutePath();
	}

}
