package com.vividsolutions.jtswebapp.client.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jtswebapp.shared.test.BufferGeomOp;
import com.vividsolutions.jtswebapp.shared.test.Test;
import com.vividsolutions.jtswebapp.shared.test.TestCase;
import com.vividsolutions.jtswebapp.shared.test.TestExecution;
import com.vividsolutions.jtswebapp.shared.test.TestRun;

public class TestRunner {
	
	private static Logger sLogger = Logger.getLogger("TestRunner");
	
	private String mTestFile;
	private TestRun mTestRun;
	
	public void run() {
		fetchTestFile();
	}

	private void fetchTestFile() {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT.getModuleBaseURL() + "TestBuffer.xml");
		rb.setCallback(new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				int code = response.getStatusCode();
				String message = response.getStatusText();
				
				if(code == Response.SC_OK) {
					mTestFile = response.getText();
					parse();
				} else {
					Exception e = new Exception(code + " " + message);
					onError(request, e);
				}
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				sLogger.log(Level.SEVERE, "Unable to fetch test file", exception);
			}
		});
		
		try {
			rb.send();
		} catch (RequestException e) {
			sLogger.log(Level.SEVERE, "Unable to fetch test file", e);
		}
	}
	
	private void parse() {
		List<TestExecution> execs = new ArrayList<>();
		
		mTestRun = new TestReader().read(mTestFile);
		
		PrecisionModel pm = null;
		switch(mTestRun.precisionModel) {
		case "FLOATING":
			pm = new PrecisionModel();
			break;
		case "FLOATING_SINGLE":
			pm = new PrecisionModel(PrecisionModel.FLOATING_SINGLE);
			break;
		case "FIXED":
			pm = new PrecisionModel(1000000);
			break;
		}
		
		GeometryFactory gf = new GeometryFactory(pm);
		WKTReader wkt = new WKTReader(gf);
		
		for(TestCase tc : mTestRun.testCases) {
			String desc = tc.description;
			
			Geometry a = null;
			try {
				a = wkt.read(tc.geometryA);
			} catch (ParseException e) {
				sLogger.log(Level.SEVERE, "Unable to parse wkt: ", e);
			}
			
			Geometry b = null;
			try {
				b = wkt.read(tc.geometryB);
			} catch (ParseException e) {
				sLogger.log(Level.SEVERE, "Unable to parse wkt: ", e);
			}
			
			for(Test t : tc.tests) {
				TestExecution te = new TestExecution();
				te.precisionModel = pm;
				te.geomFactory = gf;
				te.description = desc;
				
				switch(t.operation) {
				case "buffer":
					te.operation = new BufferGeomOp();
					break;
				}
				
				List<Object> args = new ArrayList<>();
				for(Object arg : t.arguments) {
					if(arg instanceof String) {
						String argS = (String) arg;
						
						if("A".equals(argS)) {
							args.add(a);
							continue;
						}
						
						if("B".equals(argS)) {
							args.add(b);
							continue;
						}
						
						args.add(argS);
					} else {
						args.add(arg);
					}
				}
				
				te.arguments = args.toArray();
				te.expectedResult = t.result;
				te.expectedException = t.exception;
				
				execs.add(te);
				
				te.execute();
				
				sLogger.info(te.toMethodCall());
			}
		}
	}
}
