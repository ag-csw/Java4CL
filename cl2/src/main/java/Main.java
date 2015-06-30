import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KnowledgeSourceLevel;
import cl2.CL;
import cl2.CLComment;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.debug(
				"Hello. This is the Java4CL package. The current time is {}.",
				System.currentTimeMillis());

		// Direct construction of CLCommentExpression
		String myCommentSymbol = "blah blah ...";
		LOG.debug("Eager expression instantiation starting");

		CLComment myCommentExpression = new CLComment(myCommentSymbol);

		// Testing the methods of CLCommentExpression
		assert myCommentExpression.isBasic() : "Failed check for basic expression";
		assert myCommentExpression.level() == KnowledgeSourceLevel.EXPRESSION : "Failed check for expression level";
		assert myCommentExpression.language() == CL.LANG : "Failed language check";
		// Skipping the clears
		// myCommentExpression.clearInitialValue();
		assert myCommentExpression.symbol() == myCommentSymbol : "Symbol evalution incorrect.";
		
		/*
		//Make an empty text construction
		// commented by the comment expression
		FJCLTextConstruction<CLExpression> tc1 = FJCLTextConstruction.empty();
		FJCLTextConstruction<CLExpression> tc2 = tc1.insertComments(myCommentExpression);

		// Construction of a CLCommentAsset lazily for a specific environment
		LOG.debug("Lazy asset instantiation starting");
		BasicKnowledgeAsset myCommentAsset = new CanonicalBasicKnowledgeAsset(
				CL.CL_DEFAULT_ENVIRONMENT, tc2);
		// BasicKnowledgeAssetLIMSE myCommentAsset =
		// myCommentExpression.conceptualize(env);
		assert myCommentAsset.canonicalExpression() == myCommentExpression : "Failed lazy express method on asset";

		// Construction a CLCommentAsset lazily for the default environment
		LOG.debug("Lazy asset instantiation starting");
		BasicKnowledgeAsset myCommentAsset2 = new CanonicalBasicKnowledgeAsset(
				CL.CL_DEFAULT_ENVIRONMENT, tc2);
		assert myCommentAsset2.canonicalExpression() == myCommentExpression : "Failed lazy express method on asset";
		assert myCommentAsset2.equals(myCommentAsset) : "Failed equality check on assets.";

		// Construction a CLManifestation lazily
		// CLManifestationG<Element> myManifestation;
		LOG.debug("Manifest method call starting");
		System.exit(0);
		*/
		/*
		 * myCommentManifestation = myCommentExpression.manifest(CL.xcl2dom);
		 * LOG.debug("Manifest method returns: {}", myCommentManifestation);
		 * assert myCommentManifestation.parse() == myCommentExpression :
		 * "Manifest call is not lazy";
		 * 
		 * LOG.debug("Lazy expression instantiation starting");
		 * CLCommentExpression anotherCommentExpression = CLCommentExpression
		 * .lazyNewInstance(myCommentManifestation); assert myCommentExpression
		 * != anotherCommentExpression :
		 * "Failed check that lazy new instantiation gives new instance"; assert
		 * myCommentExpression == anotherCommentExpression.manifest() .parse() :
		 * "Failed check that lazy new instantiation gives wrapper of old instance"
		 * ; assert anotherCommentExpression.isBasic() :
		 * "Failed check for basic expression";
		 * anotherCommentExpression.level();
		 * anotherCommentExpression.language(); assert
		 * anotherCommentExpression.getSymbol() == myCommentSymbol :
		 * "Symbol evalution incorrect."; anotherCommentExpression.getComment();
		 * StructuredKnowledgeExpressionLI structuredExpression = new
		 * StructuredKnowledgeExpressionLI( env, myCommentExpression,
		 * anotherCommentExpression); assert !structuredExpression.isBasic() :
		 * "Failed isBasic method on structured expression."; assert
		 * structuredExpression.level() == KnowledgeSourceLevel.EXPRESSION :
		 * "Failed level method on structured expression."; assert
		 * structuredExpression.components().contains(myCommentExpression) :
		 * "Falied component check 1"; assert
		 * structuredExpression.components().contains( anotherCommentExpression)
		 * : "Falied component check 2"; StructuredKnowledgeAssetLI
		 * structuredAsset = structuredExpression .conceptualize(env);
		 * LOG.debug("Done"); // this is not working yet
		 * LOG.debug("Lazy encode instantiation starting"); CLEncoding<Element,
		 * byte[]> myEncoding = myCommentManifestation
		 * .encode(CL.domUTF8bytearray); CLManifestationG<Element>
		 * anotherManifestation = myEncoding.decode();
		 * LOG.debug("Double Lazy instantiation : {}", anotherManifestation);
		 * anotherManifestation.value(); CLCommentManifestationG<Element>
		 * anotherCommentManifestation = (CLCommentManifestationG<Element>)
		 * anotherManifestation; anotherCommentManifestation.getSymbol();
		 * anotherCommentManifestation.getComment(); LOG.debug("Done");
		 */
	}
}
