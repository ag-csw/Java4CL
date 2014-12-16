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
import cl2.CLCommentManifestationG;
import cl2.CLEncoding;
import cl2.CLManifestationG;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		LOG.debug("Hello. This is the Java4CL package. The current time is {}.", System.currentTimeMillis());
		
		String myCommentSymbol = "blah blah ...";
		LOG.debug("Eager expression instantiation starting");
		CLCommentExpression myCommentExpression = CLCommentExpression.eagerNewInstance(myCommentSymbol);
		myCommentExpression.getLevel();
		myCommentExpression.getLanguage();
		//myCommentExpression.clearInitialValue();
		assert myCommentExpression.getSymbol() == myCommentSymbol: "Symbol evalution incorrect.";
		myCommentExpression.getComment();
		CLCommentManifestationG<Element> myCommentManifestation = null;
		try {
			LOG.debug("Manifest method call starting");
			myCommentManifestation = myCommentExpression.manifest(CL.xcl2dom);
			LOG.debug("Manifest method returns: {}", myCommentManifestation);
		} catch (DialectTypeIncompatibleException e) {
			e.printStackTrace();
		}
		assert myCommentManifestation.parse() == myCommentExpression: "Manifest call is not lazy";
		LOG.debug("Lazy expression instantiation starting");
		CLCommentExpression anotherCommentExpression = CLCommentExpression.lazyNewInstance(myCommentManifestation);
		assert  myCommentExpression != anotherCommentExpression: "Failed check that lazy new instantiation gives new instance";
		try {
			assert myCommentExpression == anotherCommentExpression.manifest().parse(): "Failed check that lazy new instantiation gives wrapper of old instance";
		} catch (DialectTypeIncompatibleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		anotherCommentExpression.getLevel();
		anotherCommentExpression.getLanguage();
		assert anotherCommentExpression.getSymbol() == myCommentSymbol: "Symbol evalution incorrect.";
		anotherCommentExpression.getComment();
		Builder builder = new GraphImmutableEnvironment.Builder();
		builder.addLanguages(CL.lang);
		GraphImmutableEnvironment env = builder.build();
		// TODO put this into cl2 package
		CL.lang.setDefaultEnvironment(env);
		LOG.debug("Checking addition of language: {}", env.containsLanguage(CL.lang) );
		try {
			LOG.debug("Lazy asset instantiation starting");
			KnowledgeAssetLI myCommentAsset = myCommentExpression.conceptualize(builder.build());
			LOG.debug("myCommentAsset: {}", myCommentAsset);
			LOG.debug("Checking lazy express method on asset : {}", myCommentAsset.express(CL.lang) == myCommentExpression);
		} catch (EnvironmentIncompatibleException | LanguageIncompatibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
		// this is not working yet
		try {
			LOG.debug("Lazy encode instantiation starting");
			CLEncoding<Element, byte[]> myEncoding = myCommentManifestation.encode(CL.domUTF8bytearray);
			CLManifestationG<Element> anotherManifestation = myEncoding.decode();
			LOG.debug("Double Lazy instantiation : {}", anotherManifestation);
			anotherManifestation.getValue();
			CLCommentManifestationG<Element> anotherCommentManifestation = (CLCommentManifestationG<Element>) anotherManifestation;
			anotherCommentManifestation.getSymbol();
			anotherCommentManifestation.getComment();
		} catch (EncodingSystemIncompatibleException e) {
			e.printStackTrace();
		}
		LOG.debug("Done");
	}

}
