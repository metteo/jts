package com.vividsolutions.jtswebapp.shared.test;

import java.util.ArrayList;
import java.util.List;

public class TestRun {
	
	public String description;
	
	public String precisionModel;
	
	public String geometryOperation;
	
	public String resultMatcher;
	
	public List<TestCase> testCases = new ArrayList<>();
}
