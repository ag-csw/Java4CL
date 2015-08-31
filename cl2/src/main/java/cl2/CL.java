package cl2;

import org.apache.commons.lang3.StringEscapeUtils;
import hashenvironment.HashFocusedKRRLanguageEnvironment;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.function.Function;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Namespace;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import api4kba.AbstractCodecSystem;
import api4kba.AbstractKRRLanguage;
import api4kba.AbstractKRRLogic;
import cl2a.CLDialect;
import cl2a.CLExpressionLike;
import cl2a.CLFormat;
import elevation.Lifter;
import elevation.Lowerer;

public final class CL {

	// private constructor to enforce non-instantiability
	private CL() {
	}

	// constants
	public static AbstractKRRLogic COMPLETE_CL_LOGIC = AbstractKRRLogic.logic("Common Logic 2");
	// call static factory method of AbstractKRRLanguage
	public static AbstractKRRLanguage LANG = AbstractKRRLanguage.language(
			"Common Logic 2", CLExpressionLike.class, COMPLETE_CL_LOGIC);

	public static HashFocusedKRRLanguageEnvironment CL_DEFAULT_ENVIRONMENT = new HashFocusedKRRLanguageEnvironment(
			CL.LANG);

	// TODO Capitalize constants
	public static CLDialect XCL2 = new CLDialectXML("XCL2");

	public static CLFormat xcl2utf8 = new CLFormat("XCL2_UTF-8", XCL2) {

		@Override
		public Charset charset() {
			return Charset.forName("UTF-8");
		}
		
	};

	public static final String URI_XCL2 = "http://purl.org/xcl/2.0/";

	public static final Namespace NS_XCL2 = Namespace.get(URI_XCL2);

	// tests for syntactic categories
	public static Boolean isComment(Object x, CLDialectType<?> dialect) {
		// TODO include a field for checking syntactic category, independent of
		// format or level
		return false;
	}

	public static AbstractCodecSystem<Element, byte[]> domUTF8bytearray = new AbstractCodecSystem<Element, byte[]>() {

		@Override
		public byte[] code(Element node) {

			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer;
			try {
				transformer = transFactory.newTransformer();
			} catch (TransformerConfigurationException e) {
				throw new RuntimeException(e);
			}
			StringWriter buffer = new StringWriter();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					"yes");
			try {
				transformer.transform(new DOMSource(node), new StreamResult(
						buffer));
			} catch (TransformerException e) {
				throw new RuntimeException(e);
			}
			try {
				return buffer.toString().getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public Element decode(byte[] bytes) {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder;
			try {
				builder = domFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				throw new RuntimeException(e);
			}
			Document doc;
			try {
				doc = builder.parse(new String(bytes, "UTF-8"));
			} catch (SAXException | IOException e) {
				throw new RuntimeException(e);
			}
			return doc.getDocumentElement();

		}
	};
	public static CLDialectType<Element> xcl2dom = new CLDialectType<Element>(
			"XCL2-dom4j", XCL2, Element.class, domUTF8bytearray) {
	};

	public static Lifter lifter() {
		return null;

	}

	public static Lowerer lowerer() {
		return null;

	}
	
	public static boolean isValidStringSymbol(String symbol){
		
	  Function<Integer, Boolean> bad = s -> (
			                   (s < 0x1)         // null
			  || ((s > 0xD7FF) && (s < 0xE000))  // surrogates
			  || ((s > 0xFFFD) && (s < 0x10000))  // not a character
			);
      int[] points = toCodePointArray(symbol);
	  for (int point :points) {
		if (bad.apply(point)){
			return false;
		}
	  }
     return true;
	}

	public static String xmlContentEncode(String text) {
		return StringEscapeUtils.escapeXml11(text);
	}

	public static String xmlAttributeEncode(String value) {
		return StringEscapeUtils.escapeXml11(value);
	}

	public static String xmlAttributeEncodeIri(CLIRI datatype) {
		return StringEscapeUtils.escapeXml11(datatype.toString());
	}
	
	// From http://www.ibm.com/developerworks/library/j-unicode/
	static int[] toCodePointArray(String str) { // Example 1-5
	    int len = str.length();          // the length of str
	    int[] acp = new int[str.codePointCount(0, len)];
	    int j = 0;                       // an index for acp

	    for (int i = 0, cp; i < len; i += Character.charCount(cp)) {
	        cp = str.codePointAt(i);
	        acp[j++] = cp;
	    }
	    return acp;
	}

}
