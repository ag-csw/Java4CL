import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import api4kb.DialectTypeIncompatibleException;
import api4kb.EncodingSystemIncompatibleException;
import api4kb.EnvironmentIncompatibleException;
import api4kb.GraphImmutableEnvironment;
import api4kb.GraphImmutableEnvironment.Builder;
import api4kb.KnowledgeAssetLI;
import api4kb.LanguageIncompatibleException;
import cl2.CL;
import cl2.CLCommentExpression;
import cl2.CLCommentManifestation;
import cl2.CLEncoding;
import cl2.CLManifestation;

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
			try {
				CLEncoding<Element, byte[]> myEncoding = myCommentManifestation.encode(CL.dom2bytearray);
				LOG.debug("Lazy instantiation : {}", myEncoding);
				CLManifestation<Element> anotherManifestation = myEncoding.decode();
				LOG.debug("Double Lazy instantiation : {}", anotherManifestation);
				anotherManifestation.getValue();
				CLCommentManifestation<Element> anotherCommentManifestation = (CLCommentManifestation<Element>) anotherManifestation;
				anotherCommentManifestation.getSymbol();
				anotherCommentManifestation.getComment();
			} catch (EncodingSystemIncompatibleException e) {
				e.printStackTrace();
			}
		} catch (DialectTypeIncompatibleException e) {
			e.printStackTrace();
		}
		Builder builder = new GraphImmutableEnvironment.Builder();
		builder.addLanguages(CL.lang);
		try {
			KnowledgeAssetLI myCommentAsset = myCommentExpression.conceptualize(builder.build());
			//myCommentAsset.express(CL.lang);
		} catch (EnvironmentIncompatibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.debug("Done");
	}

}
