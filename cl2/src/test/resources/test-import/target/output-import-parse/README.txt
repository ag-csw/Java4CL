The directory output-import-parse contains: 
  * valid XCL2 file import-max.xcl .
  * valid XCL2 file import-min.xcl .

The file import.xcl contains:
  * an XML processing instruction
  * one XCL2 text consisting of a single importation where
    the name of the imported text is an (absolute) IRI

This target resource provides a test of the output method that provides a canonical representation of the importation from the input file.
 Dependencies: Name class constructor, possibly Comment and Prefix class constructors as well
Test procedure:
* The resource is read with an XML parser and an ImportationManifestation object is created as soon as the <Import> element is encountered.
  The ImportationManifestation constructor should be lazy regarding XCL parsing, doing nothing but setting the (final, private) "xcl" field of the ImportationManifestation object.
  
The output result should be obtainable as follows:
1. Using ImportationManifestation.parse.toString.
   The toString should not be memoized directly, but should always call toXCL. 
   Dependencies: Name class constructor, possibly Comment and Prefix class constructors as well
// Call the parse method of the ImportationManifestation 
// Call the toString method of the resulting ImportationExpression 
// Concatenate the xml header with this string 
// Write the string to a file.

2. Using ImportationManifestation.parse.manifest(XCL2).
// Call the parse method of the ImportationManifestation 
// Call the manifest(XCL2) method of the resulting ImportationExpression to obtain an XML element
// Call the toString (or equivalent) method on this XML element
// Concatenate the xml header with this string 
// Write the string to a file.
 
3. Transforming the ImportationManifestation.parse object back to an ImportationManifestation object (B) and calling its toString method
// Call the ImportationManifestation contructor which takes an ImporationExpression argument alone - use ImportationManifestation.parse as the input - and produces a new ImportationManifestation (B) that contains the root element of the canonical XML representation of A in its "xcl" field.
// Call the toString method of B 
// Concatenate the xml header with this string 
// Write the string to a file.

4. Invoke a ImportationExpression constructor that copies ImportationManifestation.parse using a constructor that takes as explicit arguments all the components of each input importation, then call toString and output as before.
  * min - the Name only
  * max - the key, the Prefix sequence, the Comment, the Name
// Call an ImportationExpression constructor with arguments that are the components of A (using getters of ImportationExpression), to produce ImportationExpression B.
// Call the toString method of B 
// Concatenate the xml header with this string 
// Write the string to a file.

5. Like 1, but call an ImportationExpression constructor directly using the XML element as input, rather than explicitly constructing the intermediate manifestation.
