package com.vividsolutions.jtsexample.server;

import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.vividsolutions.jtsexample.shared.Feature;
import com.vividsolutions.jtsexample.shared.FeatureService;

/**
 * Servlet implementation class FeatureServiceServlet
 */
public class FeatureServiceServlet extends RemoteServiceServlet implements FeatureService {
	private static final long serialVersionUID = 1L;

	public Feature echo(Feature feature) {
		feature.properties.put("echo", new Date().toString());
		return feature;
	}

}
