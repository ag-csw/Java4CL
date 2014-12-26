package cl2;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

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

import api4kbj.AbstractCodecSystem;
import api4kbj.AbstractKRRDialect;
import api4kbj.AbstractKRRDialectType;
import api4kbj.AbstractKRRLanguage;

public final class CL {

	// private constructor to enforce non-instantiability
	private CL() {
	}

	// public fields
	// instantiate anonymous subclass of AbstractKRRLanguage
	public static AbstractKRRLanguage lang = new AbstractKRRLanguage(
			"Common Logic") {

		@Override
		public AbstractKRRDialectType<Element> defaultDialectType() {
			return xcl2dom;
		}

		@Override
		public AbstractKRRDialect defaultDialect() {
			return xcl2;
		}

	};

	public static CLDialect xcl2 = new CLDialect("XCL2") {
		@Override
		public CLDialectType<Element> getDefaultDialectType() {
			return xcl2dom;
		}

	};

	public static final Namespace NS_XCL2 = Namespace
			.get("http://purl.org/xcl/2.0/");

	// tests for syntactic categories
	public static Boolean isComment(Object x, CLDialectType<?> dialect) {
		// TODO include a field for checking syntactic category, independent of
		// format or level
		return false;
	}

	public static AbstractCodecSystem<Element, byte[]> domUTF8bytearray = new AbstractCodecSystem<Element, byte[]>() {

		@Override
		public byte[] code(Element node) {

				TransformerFactory transFactory = TransformerFactory
						.newInstance();
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
			"XCL2-dom4j", xcl2, Element.class, domUTF8bytearray) {
	};

}
