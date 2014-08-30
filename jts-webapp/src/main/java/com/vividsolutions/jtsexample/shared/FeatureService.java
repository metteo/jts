package com.vividsolutions.jtsexample.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("features")
public interface FeatureService extends RemoteService {
	
	Feature echo(Feature feature);

}
