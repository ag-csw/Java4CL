package cl2.xcl2;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class WellFormedXMLValidator {

		  /**
		   * Check that a String is well-formed XML.
		   *
		   * @param xml The input argument to check.
           * @throws Exception 
		   *             Thrown if the input string cannot be read
		   */
		  public static void validate(String xml) throws Exception {

		    try {
		      doValidate(xml);
		    } catch (IOException ioe) {
		      throw new Exception(
		          "Cannot parse input string. Message:" + ioe.getMessage(), ioe);
		    }
		  }

		  private static void doValidate(String xml) throws SAXException, IOException, ParserConfigurationException {
            SAXParserFactory parserBuilder = SAXParserFactory.newInstance();
            SAXParser parser = parserBuilder.newSAXParser();
		    InputSource source = new InputSource(new ByteArrayInputStream(xml.getBytes()));
		    parser.parse(source, new DefaultHandler());
		  }

		}

