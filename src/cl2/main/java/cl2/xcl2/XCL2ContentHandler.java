/**
 * 
 */
package cl2.xcl2;

//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

// import cl2.CLImportation;

/**
 * @author ralph
 *
 */
public class XCL2ContentHandler implements ContentHandler {

	
	/**
	 * 
	 */
	public XCL2ContentHandler() {
	}
	

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void startDocument() throws SAXException {
	}


	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
//		if (localName.equals(Constants.XCL_IMPORT)) {
//			CLImportation importation = CLImportationFactory.get(); 
//		}
		
	}


	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		SAXParserFactory spf = SAXParserFactory.newInstance();
//	    spf.setNamespaceAware(true);
//	    try {
//			SAXParser saxParser = spf.newSAXParser();
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//		} catch (SAXException e) {
//			e.printStackTrace();
//		}
	}

}
