The directory contains: 
  * a single valid XCL2 file import.xcl .

The file import.xcl contains:
  * an XML processing instruction
  * one XCL2 text consisting of a single importation where
    the name of the imported text is an (absolute) IRI

This target resource provides a test of the output method that provides a verbatim copy of the XML element from which the importation was constructed.
Test procedure:
* The resource is read with an XML parser and an Importation object is created as soon as the <Import> element is encountered.
  The Import constructor should be lazy regarding XCL parsing, doing nothing but setting the (final, private) "xcl" field of the Importation object.
The result should be obtainable in several different ways:
1. Using toString, which should not require XCL parsing as long as the field "xcl" is not null.
   The toString should not be memoized directly, but should always call toXCL. 
// Call the toString method of the Importation 
// Concatenate the xml header with this string 
// Write the string to a file.

2. Using toXCL, which should not require parsing as long as the field "xcl" is not null.
// Call the toXCL method of the Importation (A) to obtain an XML element
// Call the toString (or equivalent) method on this XML element
// Concatenate the xml header with this string 
// Write the string to a file.
 
3. Using the parse and stylesheet methods, as well as the evaluation mode of the toXCL method. 
   Dependencies: Name class constructor, possibly Comment and Prefix class constructors as well
// Call the parse method on A which produces a new Importation (B) that has forgotten the original XML ("xcl" is null).
// Call the stylesheet method on A which produces an XSLT transformation C 
// Call the toXCL method on Importation B producing XML element D. This requires evaluation becouase the "xcl" field of B is null.
// Apply the XSLT C to XML element D producing XML element E, which should be identical to the XML element used to construct A
// Call the toString method of E 
// Concatenate the xml header with this string 
// Write the string to a file.
