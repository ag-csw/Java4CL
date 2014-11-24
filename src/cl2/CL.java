package cl2;

import org.w3c.dom.Element;

import api4kb.KRRDialect;
import api4kb.KRRLanguage;

public final class CL {

	private CL() {
		// TODO Auto-generated constructor stub
	}
	
	public static KRRLanguage lang = new KRRLanguage("Common Logic");
	
	public static KRRDialect<Element> xcl2dom = new CLDialect<Element>("XCL2");
	public static KRRDialect<String> clif = new CLDialect<String>("CLIF");
	
	public static Boolean isComment(Object x, KRRDialect<?> dialect){
		// TODO implement test for particular dialects
		return false;
	}

}
