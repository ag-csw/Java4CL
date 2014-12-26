import graphenvironment.GraphImmutableEnvironment;
import graphenvironment.GraphImmutableEnvironment.Builder;
import krhashmap.KnowledgeAssetLI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import api4kbj.ImmutableEnvironment;
import cl2.CL;
import cl2.CLCommentExpression;
import cl2.CLCommentManifestationG;
import cl2.CLEncoding;
import cl2.CLManifestationG;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		GraphImmutableEnvironment env = (GraphImmutableEnvironment) CL.lang.defaultEnvironment();
		LOG.debug(
				"Hello. This is the Java4CL package. The current time is {}.",
				System.currentTimeMillis());

		String myCommentSymbol = "blah blah ...";
		LOG.debug("Eager expression instantiation starting");
		CLCommentExpression myCommentExpression = CLCommentExpression
				.eagerNewInstance(myCommentSymbol);
		myCommentExpression.level();
		myCommentExpression.language();
		// myCommentExpression.clearInitialValue();
		assert myCommentExpression.getSymbol() == myCommentSymbol : "Symbol evalution incorrect.";
		myCommentExpression.getComment();
		CLCommentManifestationG<Element> myCommentManifestation = null;
		LOG.debug("Manifest method call starting");
		myCommentManifestation = myCommentExpression.manifest(CL.xcl2dom);
		LOG.debug("Manifest method returns: {}", myCommentManifestation);
		assert myCommentManifestation.parse() == myCommentExpression : "Manifest call is not lazy";
		LOG.debug("Lazy expression instantiation starting");
		CLCommentExpression anotherCommentExpression = CLCommentExpression
				.lazyNewInstance(myCommentManifestation);
		assert myCommentExpression != anotherCommentExpression : "Failed check that lazy new instantiation gives new instance";
		assert myCommentExpression == anotherCommentExpression.manifest()
				.parse() : "Failed check that lazy new instantiation gives wrapper of old instance";
		anotherCommentExpression.level();
		anotherCommentExpression.language();
		assert anotherCommentExpression.getSymbol() == myCommentSymbol : "Symbol evalution incorrect.";
		anotherCommentExpression.getComment();
		LOG.debug("Checking addition of language: {}",
				env.containsLanguage(CL.lang));
		LOG.debug("Lazy asset instantiation starting");
		KnowledgeAssetLI myCommentAsset = myCommentExpression
				.conceptualize(env);
		LOG.debug("myCommentAsset: {}", myCommentAsset);
		LOG.debug("Checking lazy express method on asset : {}",
				myCommentAsset.express(CL.lang) == myCommentExpression);
		System.exit(0);
		// this is not working yet
		LOG.debug("Lazy encode instantiation starting");
		CLEncoding<Element, byte[]> myEncoding = myCommentManifestation
				.encode(CL.domUTF8bytearray);
		CLManifestationG<Element> anotherManifestation = myEncoding.decode();
		LOG.debug("Double Lazy instantiation : {}", anotherManifestation);
		anotherManifestation.value();
		CLCommentManifestationG<Element> anotherCommentManifestation = (CLCommentManifestationG<Element>) anotherManifestation;
		anotherCommentManifestation.getSymbol();
		anotherCommentManifestation.getComment();
		LOG.debug("Done");
	}

}
