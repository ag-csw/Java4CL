import java.io.InputStream;

import graphenvironment.FocusedGraphImmutableEnvironment;
import krconfigured.KnowledgeResourceConfiguredTemplate;
import krhashmap.li.mse.BasicKnowledgeAssetLIMSE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.CodecSystem;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeSourceLevel;
import cl2.CL;
import cl2.CLCommentExpression;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.debug(
				"Hello. This is the Java4CL package. The current time is {}.",
				System.currentTimeMillis());


		// Direct construction of CLCommentExpression
		String myCommentSymbol = "blah blah ...";
		LOG.debug("Eager expression instantiation starting");
		KnowledgeResourceConfiguredTemplate basicExpressionTemplate = new KnowledgeResourceConfiguredTemplate() {

			@Override
			public KnowledgeSourceLevel level() {
				return KnowledgeSourceLevel.EXPRESSION;
			}

			@Override
			public boolean isBasic() {
				return true;
			}

			@Override
			public FocusedImmutableEnvironment defaultEnvironment() {
				return CL.CL_DEFAULT_ENVIRONMENT;
			}

			@Override
			public KRRLanguage defaultLanguage() {
				return CL.LANG;
			}

			@Override
			public KRRDialect defaultDialect() {
				return CL.XCL2;
			}

			@Override
			public KRRDialectType<?> defaultDialectType() {
				return CL.xcl2dom;
			}

			@Override
			public KRRFormat defaultFormat() {
				return CL.xcl2utf8;
			}

			@Override
			public KRRFormatType<?> defaultFormatType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public CodecSystem<?, ?> defaultCodecSystem() {
				return CL.domUTF8bytearray;
			}

			@Override
			public Object defaultReceiver() {
				return System.out;
			}

			@Override
			public InputStream defaultInput() {
				return System.in;
			}

		};
		CLCommentExpression myCommentExpression = CLCommentExpression
				.eagerNewInstance(basicExpressionTemplate, myCommentSymbol);

		// Testing the methods of CLCommentExpression
		assert myCommentExpression.isBasic() : "Failed check for basic expression";
		assert myCommentExpression.level() == KnowledgeSourceLevel.EXPRESSION : "Failed check for expression level";
		assert myCommentExpression.language() == CL.LANG : "Failed language check";
		// Skipping the clears
		// myCommentExpression.clearInitialValue();
		assert myCommentExpression.getSymbol() == myCommentSymbol : "Symbol evalution incorrect.";
		assert myCommentExpression.getComment().isEmpty() : "Failed nested comment is absent";

		// Construction of a CLCommentAsset lazily for a specific environment
		LOG.debug("Lazy asset instantiation starting");
		BasicKnowledgeAssetLIMSE myCommentAsset = new BasicKnowledgeAssetLIMSE(
				myCommentExpression, CL.CL_DEFAULT_ENVIRONMENT);
		// BasicKnowledgeAssetLIMSE myCommentAsset =
		// myCommentExpression.conceptualize(env);
		assert myCommentAsset.express(CL.LANG) == myCommentExpression : "Failed lazy express method on asset";

		// Construction a CLCommentAsset lazily for the default environment
		LOG.debug("Lazy asset instantiation starting");
		BasicKnowledgeAssetLIMSE myCommentAsset2 = new BasicKnowledgeAssetLIMSE(
				myCommentExpression, CL.CL_DEFAULT_ENVIRONMENT);
		assert myCommentAsset2.express(CL.LANG) == myCommentExpression : "Failed lazy express method on asset";
		assert myCommentAsset2.equals(myCommentAsset) : "Failed equality check on assets.";

		// Construction a CLManifestation lazily
		// CLManifestationG<Element> myManifestation;
		LOG.debug("Manifest method call starting");
		System.exit(0);
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
