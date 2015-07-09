Java4CL
=======

A Java library for ISO Common Logic ontologies.

Coverage: [![codecov.io](http://codecov.io/github/ag-csw/Java4CL/coverage.svg?branch=java8)](http://codecov.io/github/ag-csw/Java4CL?branch=java8)
--------
![codecov.io](http://codecov.io/github/ag-csw/Java4CL/branch.svg?branch=java8)

Build instructions
------------------

The build process is managed by Maven.

Open a terminal, navigate to the project root directory *Java4CL* and type:

    mvn install

### Eclipse

In order to import the project into Eclipse, in addition to the above instructions, type:

    mvn eclipse:eclipse
    
Then import the project:

1. From the menu, select *File / Import...*.
2. Select *Maven / Existing Maven Projects*.
3. Navigate to the project rool folder and select it.

During development it might be desirable to make use of Eclipse's automatic
building mechanism.

**Note:** There seems to be a problem with the Scala tools in combination with automatic building enabled in Eclipse, which leads to false Scala related error messages in Scala source editors and Eclipse's problem view. Therefore, it might be desirable to turn Eclipse's auto build feature off while working with Scala. The Scala syntax checker will still work with automatic building disabled, except for cross-project references.

In order to keep Eclipse's classpath information consistent
with the information provided by Maven, after changes to the classpath, right
click on the project root and select "Maven \> Update project...".

