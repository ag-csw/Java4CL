The directory contains: 
  * a valid XCL2 file import.xcl .
  * XSLT files containing styling information for the XCL2 file as a manifestation.

The file import.xcl contains:
  * an XML processing instruction
  * one XCL2 text consisting of a single importation where
    the name of the imported text is an (absolute) IRI

This resource provides a minimal XCL2 importation, the only optional component being the processing instruction.

The <xsl:element parts of the stylesheets contain the actualy styling information for the corresponding objects. The rest of the XSLT is boilerplate that is necessary only to test the stylesheet with an XSLT processor.
