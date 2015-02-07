package com.vividsolutions.jtswebapp.client.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
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
		mTestRun = new TestReader().read(mTestFile);
	}
}
