package cl2;

import org.w3c.dom.Element;

import api4kb.KRRDialect;
import api4kb.AbstractKRRLanguage;
import api4kb.KRRLanguage;
import org.dom4j.*;

public final class CL {

	// private constructor to enforce non-instanciability
	private CL() {}
	
	// public fields
	// instantiate anonymous subclass of AbstractKRRLanguage
	public static KRRLanguage lang = new AbstractKRRLanguage("Common Logic") {

		@Override
		public KRRDialect<Element> defaultDialect() {
			return xcl2dom;
		}};	 
	// uses static factory methods on CLDialect
	public static CLDialect<Element> xcl2dom = CLDialect.newInstance("XCL2-dom4j");
	public static CLDialect<String> clif = CLDialect.newInstance("CLIF");
	public static final Namespace NS_XCL2 = Namespace.get("http://purl.org/xcl/2.0/");	
	// tests for syntactic categories
	public static Boolean isComment(Object x, KRRDialect<?> dialect){
		// TODO implement case tests for particular dialects
		return false;
	}

}
