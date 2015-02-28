package cl2;

import elevation.Lifter;
import elevation.Lowerer;
import hashenvironment.HashFocusedKRRLanguageEnvironment;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

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
import api4kbj.EqSet;

public final class CL {

	// private constructor to enforce non-instantiability
	private CL() {
	}

	// constants
	public static EqSet COMPLETE_CL_LOGIC = new EqSet() {

		@Override
		public String name() {
			return "Complete Common Logic Language";
		}
	};
	// call static factory method of AbstractKRRLanguage
	public static AbstractKRRLanguage LANG = AbstractKRRLanguage.language(
			"Common Logic", CLExpression.class, COMPLETE_CL_LOGIC);

	public static HashFocusedKRRLanguageEnvironment CL_DEFAULT_ENVIRONMENT = new HashFocusedKRRLanguageEnvironment(
			CL.LANG);

	// TODO Capitalize constants
	public static CLDialect XCL2 = new CLDialect("XCL2") {
	};

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

}
