The directory output-import-toString contains: 
  * valid XCL2 file import-max.xcl .
  * valid XCL2 file import-min.xcl .

These files import.xcl contain the output of the test applied to the input files:
* input-import-max/input.xcl
* input-import-min/input.xcl

This target resource provides tests of the output methods that provides a verbatim copy of the XML element from which the importation was constructed.
Test procedure:
* The resource is read with an XML parser and an ImportationManifestation object is created as soon as the <Import> element is encountered.
  The ImportationManifestation constructor should be lazy regarding XCL parsing, doing nothing but setting the (final, private) "xcl" field of the ImportationManifestation object.
The output result should be obtainable in several different ways:
1. Using ImportationManifestation.toString, which should not require XCL parsing as long as the field "xcl" is not null.
   The toString should not be memoized directly, but should call ImportationManifestation.toXCL when field "xcl" is not null. 
// Call the toString method of the ImportationManifestation 
// Concatenate the xml header with this string 
// Write the string to a file.

2. Using ImportationManifestation.toXCL directly, which should not require parsing as long as the field "xcl" is not null.
// Call the ImportationManifestation.toXCL method of the ImportationManifestation (A) to obtain an XML element
// Call the toString (or equivalent) method on this XML element
// Concatenate the xml header with this string 
// Write the string to a file.
 
3. Using the ImportationManifestation.parse and ImportationManifestation.stylesheet methods, as well as the ImportationExpression.toXCL method. 
   Dependencies: Name class constructor, possibly Comment and Prefix class constructors as well
// Call the parse method on A which produces a new ImportationExpression (B) that has forgotten the original XML (the "xcl" field is null).
// Call the stylesheet method on A which produces an XSLT transformation C 
// Call the ImportationExpression.toXCL method on ImportationExpression B producing XML element D. This requires evaluation becouase the "xcl" field of B is null.
// Apply the XSLT C to XML element D producing XML element E, which should be identical to the XML element used to construct A
// Call the toString method of E 
// Concatenate the xml header with this string 
// Write the string to a file.
