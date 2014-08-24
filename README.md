JTS Topology Suite
==================

[![Build Status](https://travis-ci.org/metteo/jts.svg?branch=master)](https://travis-ci.org/metteo/jts)

Fork (or a mirror) of original project from: svn://svn.code.sf.net/p/jts-topo-suite/code

Original project site: http://tsusiatsoftware.net/jts/main.html (previous: http://www.vividsolutions.com/jts/JTSHome.htm)
     
Welcome to the repository for the JTS Topology Suite.
JTS essentially consists of several Java modules,
each one corresponding to a separate JAR file.
Only jts-core is necessary to use the library in an application.
The others are external tools or optional extensions. To
read more about specific modules check pom.xml description.

[Project site](http://metteo.github.io/jts/web/index.html)

Develope JTS
------------
Java IDEs support Maven projects out of the box. Just import them, the rest should be automatically configured. Some jts-io-* projects require additional jars which are not easily obtainable. Check poms for more information.

Build JTS
---------
The JTS library is intended to be Java 1.5 compatible (to permit deployment on mobile platforms and some primitive databases). The tools are not subject to this limitation, so you can target Java 1.6 or higher.

* In the root directory execute

  mvn install

Test JTS
--------
* Java unit tests can be executed using
 
  mvn test

* XML tests run during integration-test phase of jts-test-library project:

  mvn integration-test
  
* Some XML tests are executed separately since they should fail. Until
test runner doesn't support such case use:

  mvn exec:exec

Deploy JTS
----------
JTS snapshot build is be deployed to GitHub pages based, Maven repository.

[Maven Snapshots](http://metteo.github.io/jts/maven/snapshots)
[Maven Releases](http://metteo.github.io/jts/maven/releases)
