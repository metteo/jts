package com.vividsolutions.jtswebapp.shared;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FeatureServiceAsync {

	Request echo(Feature feature, AsyncCallback<Feature> callback);

}
