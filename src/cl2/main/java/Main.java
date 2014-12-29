import graphenvironment.GraphImmutableEnvironment;
import krhashmap.BasicKnowledgeAssetLI;
import krhashmap.StructuredKnowledgeAssetLI;
import krhashmap.StructuredKnowledgeExpressionLI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import api4kbj.API4KB;
import api4kbj.KnowledgeSourceLevel;
import cl2.CL;
import cl2.CLCommentExpression;
import cl2.CLCommentManifestationG;
import cl2.CLEncoding;
import cl2.CLManifestationG;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.debug(
				"Hello. This is the Java4CL package. The current time is {}.",
				System.currentTimeMillis());

		// Testing CL.lang
		assert CL.lang.defaultDialect().equals(CL.xcl2) : "Failed set of default dialect for CL.lang.";
		assert CL.lang.defaultDialectType().equals(CL.xcl2dom) : "Failed set of default dialect type for CL.lang.";
		assert CL.lang.defaultSystem().equals(API4KB.CodecSystemXMLUTF8) : "Failed set of default codec system for CL.lang.";
		assert CL.lang.name().equals("Common Logic") : "Failed set of name for CL.lang.";

		GraphImmutableEnvironment env = (GraphImmutableEnvironment) CL.lang
				.defaultEnvironment();
		assert CL.lang.equals(env.defaultLanguage()) : "Failed set of default language in environment construction";
		assert CL.lang.equals(env.focusLanguage().value()) : "Failed set of default language in environment construction";

		// Direct construction of CLCommentExpression
		String myCommentSymbol = "blah blah ...";
		LOG.debug("Eager expression instantiation starting");
		CLCommentExpression myCommentExpression = CLCommentExpression
				.eagerNewInstance(myCommentSymbol);

		// Testing the methods of CLCommentExpression
		assert myCommentExpression.isBasic() : "Failed check for basic expression";
		assert myCommentExpression.level() == KnowledgeSourceLevel.EXPRESSION : "Failed check for expression level";
		assert myCommentExpression.language() == CL.lang : "Failed language check";
		// Skipping the clears
		// myCommentExpression.clearInitialValue();
		assert myCommentExpression.getSymbol() == myCommentSymbol : "Symbol evalution incorrect.";
		assert myCommentExpression.getComment().isEmpty() : "Failed nested comment is absent";

		// Construction a CLCommentAsset lazily for a specific environment
		LOG.debug("Lazy asset instantiation starting");
		BasicKnowledgeAssetLI myCommentAsset = myCommentExpression
				.conceptualize(env);
		assert myCommentAsset.express(CL.lang) == myCommentExpression : "Failed lazy express method on asset";

		// Construction a CLCommentAsset lazily for the default environment
		LOG.debug("Lazy asset instantiation starting");
		BasicKnowledgeAssetLI myCommentAsset2 = myCommentExpression
				.conceptualize();
		assert myCommentAsset2.express(CL.lang) == myCommentExpression : "Failed lazy express method on asset";
		assert myCommentAsset2.equals(myCommentAsset) : "Failed equality check on assets.";

		// Construction a CLCommentManifestation lazily
		CLCommentManifestationG<Element> myCommentManifestation;
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
		assert anotherCommentExpression.isBasic() : "Failed check for basic expression";
		anotherCommentExpression.level();
		anotherCommentExpression.language();
		assert anotherCommentExpression.getSymbol() == myCommentSymbol : "Symbol evalution incorrect.";
		anotherCommentExpression.getComment();
		LOG.debug("Checking addition of language: {}",
				env.containsLanguage(CL.lang));
		StructuredKnowledgeExpressionLI structuredExpression = new StructuredKnowledgeExpressionLI(
				env, myCommentExpression, anotherCommentExpression);
		assert !structuredExpression.isBasic() : "Failed isBasic method on structured expression.";
		assert structuredExpression.level() == KnowledgeSourceLevel.EXPRESSION : "Failed level method on structured expression.";
		assert structuredExpression.components().contains(myCommentExpression) : "Falied component check 1";
		assert structuredExpression.components().contains(
				anotherCommentExpression) : "Falied component check 2";
		StructuredKnowledgeAssetLI structuredAsset = structuredExpression
				.conceptualize(env);
		LOG.debug("Done");
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
