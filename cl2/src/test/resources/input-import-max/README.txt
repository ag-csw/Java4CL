This directory contains: 
  * a valid XCL2 file import.xcl .
  * XSLT files containing styling information for the XCL2 file as a manifestation.

The file import.xcl contains:
  * an XML processing instruction
  * one XCL2 text consisting of a single importation that
    - has a key attribute
    - has an xml:base attribute
    - has an attached XCL2 Comment with a key that is a relative IRI
    - has an embedded XML comment
    - has two Prefix definitions, 
      o one of which is used in the CURIE for the name of the imported text
      o the other is used in the CURIE for the key of the importation
    -has a CURIE for the name of the imported text
 
This resource provides a maximal XCL2 importation where all optional features available for an importation are used.

The <xsl:element parts of the stylesheets contain the actualy styling information for the corresponding objects. The rest of the XSLT is boilerplate that is necessary only to test the stylesheet with an XSLT processor.
      