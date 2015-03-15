package com.vividsolutions.jtswebapp.shared.test;

import java.util.Arrays;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

public class TestExecution {
	public PrecisionModel precisionModel;
	public GeometryFactory geomFactory;
	
	public String description;
	
	public GeomOp operation;
	public Object[] arguments;
	
	public String expectedResult;
	public String expectedException;
	
	public Object actualResult;
	public Exception actualException;
	
	public String toMethodCall() {
		return operation + "(" + Arrays.toString(arguments) + ") " + (isSuccess() ? "OK" : "FAIL");
	}
	
	public void execute() {
		try {
			actualResult = operation.invoke(arguments);
		} catch (Exception e) {
			actualException = e;
		}
	}
	
	public boolean isSuccess() {
		if(expectedResult != null) {
			return expectedResult.equals(actualResult.toString());
		} else {
			return expectedException.equals(actualException.getClass().getName());
		}
	}
}
