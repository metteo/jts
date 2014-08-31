package com.vividsolutions.jts.geom;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKBWriter;

public abstract class GeometrySerializer {
	
	private static GeometryFactory sFactory = new GeometryFactory();
	private static WKBWriter sWriter = new WKBWriter(3, true);
	private static WKBReader sReader = new WKBReader(sFactory);
	
	public static GeometryFactory getFactory() {
		return sFactory;
	}
	
	public static void setFactory(GeometryFactory factory) {
		if(factory == null){
			throw new IllegalArgumentException();
		}
		
		sFactory = factory;
		sReader = new WKBReader(sFactory);
	}
	
	public static WKBReader getReader() {
		return sReader;
	}
	
	public static WKBWriter getWriter() {
		return sWriter;
	}
	
	public static boolean hasFactory() {
		return sFactory != null;
	}
	
	public static Geometry instantiate(SerializationStreamReader streamReader)
			throws SerializationException {
		WKBReader reader = sReader;
		
		String hex = streamReader.readString();
		byte[] wkb = WKBReader.hexToBytes(hex);
		
		
		try {
			Geometry g = reader.read(wkb);
			return g;
		} catch (ParseException e) {
			throw new SerializationException(e);
		}
	}

	public static void serialize(SerializationStreamWriter streamWriter,
			Geometry instance) throws SerializationException {
		WKBWriter writer = sWriter;
		
		byte[] wkb = writer.write(instance);
		String hex = WKBWriter.toHex(wkb);
		streamWriter.writeString(hex);
	}
}
