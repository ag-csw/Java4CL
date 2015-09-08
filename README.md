Java4CL
=======

A Java library for ISO Common Logic ontologies.

Coverage: [![codecov.io](http://codecov.io/github/ag-csw/Java4CL/coverage.svg?branch=java8)](http://codecov.io/github/ag-csw/Java4CL?branch=java8)
--------
![codecov.io](http://codecov.io/github/ag-csw/Java4CL/branch.svg?branch=java8)

Continuous Integration using [Travis CI](https://travis-ci.org/ag-csw/Java4CL).

Java
----
Java 1.8 is required for this project, primarily for the default methods in interfaces. Occasionally, lambda expressions and method references are also used.

Build instructions
------------------

The build process is managed by Maven.

Open a terminal, navigate to the project root directory *Java4CL* and type:

    mvn install -DskipTests=true -Dmaven.javadoc.skip=true

### Eclipse
A Maven plugin for Eclipse is required. The M2Eclipse plugin (http://www.eclipse.org/m2e/) has been tested. The maven-eclipse-plugin has not been tested.

Both JUnit and ScalaTest are used for testing this project. Developers should add Scala support to their Eclipse IDE. Starting with a plain Eclipse for Java developers (Luna or Mars), the following plugin configuration has been tested and found to be adequate:

1. m2e - Maven Integration for Eclipse (1.6)
2. m2e - slf4j over logback logging (1.6)
3. Maven Integration for Scala IDE (0.5)
4. sbt 0.13.6
5. Scala 2.11.7
6. Scala IDE for Eclipse 4.1.1
7. ScalaTest for Scala IDE 2.9.3
 
The 1.8 JVM should be added to the Eclipse Java Compiler preferences if it is not already there.

In order to import the project into Eclipse, in addition to the above instructions, type:

    mvn eclipse:eclipse
    
Then import the project:

1. From the menu, select *File / Import...*.
2. Select *Maven / Existing Maven Projects*.
3. Navigate to the project root folder and select it.

This importation should create Maven, Java and Scala natures as appropriate for the Maven (sub-)modules.

During development it might be desirable to make use of Eclipse's automatic
building mechanism.

**Note:** There seems to be a problem with the Scala tools in combination with automatic building enabled in Eclipse, which leads to false Scala related error messages in Scala source editors and Eclipse's problem view. Therefore, it might be desirable to turn Eclipse's auto build feature off while working with Scala. The Scala syntax checker will still work with automatic building disabled, except for cross-project references. Sometimes it helps to restart the Scala presentation compiler (right-click on project > Scala > Restart ...

In order to keep Eclipse's classpath information consistent
with the information provided by Maven, after changes to the classpath, right
click on the project root and select "Maven \> Update project...".

Documentation
------------------
For complete documentation, please see http://ag-csw.github.io/Java4CL/
