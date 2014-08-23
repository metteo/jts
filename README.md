JTS Topology Suite
==================

Fork (or rather mirror) of original project from: svn://svn.code.sf.net/p/jts-topo-suite/code
Original project site: http://www.vividsolutions.com/jts/JTSHome.htm
     
Welcome to the repository for the JTS Topology Suite.
JTS essentially consists of several Java modules,
each one corresponding to a separate JAR file.
Only jts-core is necessary to use the library in an application.
The others are external tools or optional extensions.

Repository Structure
--------------------

* jts - Parent pom project with configuration, documentation, site code
* jts-core - Core JTS module
* jts-io - parent project for I/O drivers for proprietary formats
* jts-io-oracle - I/O drivers for Oracle format
* jts-io-esri - I/O drivers for ESRI SDE format
* jts-app - Applications & tools for working with JTS
* jts-example - Examples of working JTS code
* jts-lab - Code which is experimental or under construction, provided for early access
* jts-jump - A JUMP plugin exposing some JTS functions (unmaintained - in original SVN)
* jts-sde-adapter - an older driver for ArcSDE (unmaintained - in original SVN)
* libjts - A wrapper for building JTS with GCJ (unmaintained - in original SVN)
* bin - Scripts for running JTS tools on various platforms
* LICENSE - license file

Build JTS
---------

The JTS library is intended to be Java 1.4 compatible
(to permit deployment on mobile platforms and some primitive databases).
The tools are not subject to this limitation, so target Java 1.6 or higher.

* In the root directory execute

  mvn install

Test JTS
--------

* Java unit tests can be executed using
 
  mvn test
  
TODO: update instructions after finalizng execution of xml tests
* The XML test files can also be run, using the TestRunner application.
  This is invoked by the testrunner shell script, and may
  also be run from inside an IDE.
  At the JTS root dir run:
  
  testrunner -files jts/testxml/general jts/testxml/validate 

Deploy JTS
----------

TODO: deploy snapshots after running on CI server, deploy releases ?

Configure JTS in Eclipse
------------------------

Use m2eclipse plugin to import projects to Eclipse. jts-io-* projects require additional jars which are not easily obtainable.

Run Configurations 
^^^^^^^^^^^^^^^^^^

Useful JTS tools:

* JTS TestBuilder - com.vividsolutions.jtstest.testbuilder.JTSTestBuilder
** VM args: -Xmx1000M
** Optional VM args (on Mac): -Dswing.defaultlaf=javax.swing.plaf.metal.MetalLookAndFeel

* JTS XML Tests - com.vividsolutions.jtstest.testrunner.TopologyTestApp
** Program arguments: -files jts/testxml/general jts/testxml/validate  
** Working Directory: <repo root>


