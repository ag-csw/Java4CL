package cl2;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Namespace;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import api4kb.AbstractCodecSystem;
import api4kb.AbstractKRRDialect;
import api4kb.AbstractKRRDialectType;
import api4kb.AbstractKRRLanguage;
import api4kb.DecoderException;
import api4kb.EncoderException;



public final class CL {

	// private constructor to enforce non-instanciability
	private CL() {}
	
	// public fields
	// instantiate anonymous subclass of AbstractKRRLanguage
	public static AbstractKRRLanguage lang = new AbstractKRRLanguage("Common Logic") {

		@Override
		public AbstractKRRDialectType<Element> defaultDialectType() {
			return xcl2dom;
		}

		@Override
		public AbstractKRRDialect defaultDialect() {
			return xcl2;
		}

		};	 
		
		
	public static CLDialect xcl2 = new CLDialect("XCL2") {};
	
	
	public static final Namespace NS_XCL2 = Namespace.get("http://purl.org/xcl/2.0/");	
	// tests for syntactic categories
	public static Boolean isComment(Object x, CLDialectType<?> dialect){
		// TODO include a field for checking syntactic category, independent of format or level
		return false;
	}
	
	public static AbstractCodecSystem<Element, byte[]> domUTF8bytearray = new AbstractCodecSystem<Element, byte[]>(){

		@Override
		public byte[] code(Element node) throws EncoderException, TransformerException, UnsupportedEncodingException, IOException {
			
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			StringWriter buffer = new StringWriter();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(node),
			      new StreamResult(buffer));
			return buffer.toString().getBytes("UTF-8");
		}

		@Override
		public Element decode(byte[] bytes) throws DecoderException, ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(new String(bytes, "UTF-8"));
			return doc.getDocumentElement();
		}};
		public static CLDialectType<Element> xcl2dom = 
				new CLDialectType<Element>("XCL2-dom4j", xcl2, Element.class, domUTF8bytearray){}; 

}


