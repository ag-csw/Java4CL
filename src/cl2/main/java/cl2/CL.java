package cl2;

import org.w3c.dom.Element;

import api4kb.KRRDialect;
import api4kb.KRRDialectType;
import api4kb.AbstractKRRLanguage;
import org.dom4j.*;

public final class CL {

	// private constructor to enforce non-instanciability
	private CL() {}
	
	// public fields
	// instantiate anonymous subclass of AbstractKRRLanguage
	public static AbstractKRRLanguage lang = new AbstractKRRLanguage("Common Logic") {

		@Override
		public KRRDialectType<Element> defaultDialectType() {
			return xcl2dom;
		}

		@Override
		public KRRDialect defaultDialect() {
			return xcl2;
		}

		};	 
		
		
	public static CLDialect xcl2 = new CLDialect("XCL2") {
		
	};
	
	public static CLDialectType<Element> xcl2dom = 
			new CLDialectType<Element>("XCL2-dom4j", xcl2, Element.class){}; 
	
	public static final Namespace NS_XCL2 = Namespace.get("http://purl.org/xcl/2.0/");	
	// tests for syntactic categories
	public static Boolean isComment(Object x, KRRDialectType<?> dialect){
		// TODO implement case tests for particular dialects
		return false;
	}

}
