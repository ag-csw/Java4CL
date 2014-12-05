import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import api4kb.DialectIncompatibleException;
import cl2.CL;
import cl2.CLCommentExpression;
import cl2.CLCommentManifestation;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		LOG.debug("Hello. This is the Java4CL package. The current time is {}.", System.currentTimeMillis());
		
		String myCommentSymbol = "blah blah ...";
		CLCommentExpression myCommentExpression = CLCommentExpression.eagerNewInstance(myCommentSymbol);
		CLCommentManifestation<Element> myCommentManifestation;
		try {
			myCommentManifestation = myCommentExpression.manifest(CL.xcl2dom);
			CLCommentExpression anotherCommentExpression = CLCommentExpression.lazyNewInstance(myCommentManifestation);
			anotherCommentExpression.getSymbol();
			anotherCommentExpression.getComment();
		} catch (DialectIncompatibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
