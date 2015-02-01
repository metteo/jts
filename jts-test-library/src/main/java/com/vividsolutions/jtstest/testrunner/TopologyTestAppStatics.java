package com.vividsolutions.jtstest.testrunner;

import com.vividsolutions.jtstest.function.GeometryFunctionRegistry;
import com.vividsolutions.jtstest.function.TestCaseGeometryFunctions;
import com.vividsolutions.jtstest.geomop.GeometryFunctionOperation;
import com.vividsolutions.jtstest.geomop.GeometryOperation;

public class TopologyTestAppStatics {
  private static GeometryFunctionRegistry funcRegistry 
  	= new GeometryFunctionRegistry(TestCaseGeometryFunctions.class);
  private static GeometryOperation defaultOp = new GeometryFunctionOperation(funcRegistry);
  private static GeometryOperation geometryOp = defaultOp;

  public static GeometryFunctionRegistry getFuncRegistry() {
	  return funcRegistry;
  }
  
  public static GeometryOperation getGeometryOperation()
  {
    return geometryOp;
  }
  
  /*package*/ static void setGeometryOperation(GeometryOperation go) {
	  geometryOp = go;
  }

  /**
   * Tests whether a GeometryOperation was specified on the command line
   * @return true if a geometry operation was specified
   */
  public static boolean isGeometryOperationSpecified()
  {
  	return geometryOp != defaultOp;
  }
  
  private static ResultMatcher defaultResultMatcher = new EqualityResultMatcher();
  private static ResultMatcher resultMatcher = defaultResultMatcher;

  public static ResultMatcher getResultMatcher()
  {
    return resultMatcher;
  }
  /**
   * Tests whether a {@link ResultMatcher} was specified on the command line
   * @return true if a matcher was specified
   */
  public static boolean isResultMatcherSpecified()
  {
  	return resultMatcher != defaultResultMatcher;
  }
}
