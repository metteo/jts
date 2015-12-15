package com.vividsolutions.jtswebapp.client;

import com.google.gwt.core.client.EntryPoint;

public class JTSExportEntry implements EntryPoint {

	public void onModuleLoad() {
		fireJtsReady();
	}

	private static native void fireJtsReady()/*-{
		var event;
		var type = "jtsready";

		if ($doc.createEvent) {
			event = $doc.createEvent("Events");
			event.initEvent(type, true, true);
		} else {
			event = $doc.createEventObject();
			event.eventType = type;
		}

		event.eventName = type;

		if ($doc.createEvent) {
			$wnd.dispatchEvent(event);
		} else {
			$wnd.fireEvent("on" + event.eventType, event);
		}
	}-*/;

}
