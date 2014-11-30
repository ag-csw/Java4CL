package cl2;

import org.w3c.dom.Element;

import api4kb.KRRDialect;
import api4kb.AbstractKRRLanguage;
import api4kb.KRRLanguage;

public final class CL {

	// private constructor to enforce non-instanciability
	private CL() {}
	
	// public fields
	// instantiate anonymous subclass of AbstractKRRLanguage
	public static KRRLanguage lang = new AbstractKRRLanguage("Common Logic") {};	 
	// uses static factory methods on CLDialect
	public static CLDialect<Element> xcl2dom = CLDialect.newInstance("XCL2");
	public static CLDialect<String> clif = CLDialect.newInstance("CLIF");
	
	// tests for syntactic categories
	public static Boolean isComment(Object x, KRRDialect<?> dialect){
		// TODO implement case tests for particular dialects
		return false;
	}

}
