package cl2;

import org.w3c.dom.Element;

import api4kb.KRRDialect;
import api4kb.KRRLanguage;

public final class CL {

	// private constructor to enforce non-instantiability
	private CL() {}
	
	// public fields
	//TODO use static factory methods on KRRLanguage and CLDialect
	//     to reuse a single instance
	public static KRRLanguage lang = new KRRLanguage("Common Logic");	 
	public static KRRDialect<Element> xcl2dom = new CLDialect<Element>("XCL2");
	public static KRRDialect<String> clif = new CLDialect<String>("CLIF");
	
	// tests for syntactic categories
	public static Boolean isComment(Object x, KRRDialect<?> dialect){
		// TODO implement case tests for particular dialects
		return false;
	}

}
