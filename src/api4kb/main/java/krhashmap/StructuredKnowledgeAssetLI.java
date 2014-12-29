package krhashmap;

import java.util.HashSet;
import graphenvironment.GraphImmutableEnvironment;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeExpression;
import api4kbj.StructuredKnowledgeAsset;

public class StructuredKnowledgeAssetLI extends AbstractKnowledgeAsset
		implements StructuredKnowledgeAsset {

	public StructuredKnowledgeAssetLI(GraphImmutableEnvironment env) {
		super(env, false);
	}

	public StructuredKnowledgeAssetLI(AbstractKnowledgeExpression expression,
			GraphImmutableEnvironment env) {
		super(expression, env);
		// TODO Auto-generated constructor stub
	}

	private Iterable<KnowledgeAsset> components;

	@Override
	public Iterable<KnowledgeAsset> components() {
		return components;
	}

	@Override
	AbstractBasicKnowledgeExpression newExpression(KRRLanguage lang) {
		// TODO Auto-generated method stub
		return null;
	}

	public static StructuredKnowledgeAssetLI lazyNewInstance(
			StructuredKnowledgeExpressionLI structuredKnowledgeExpressionLI,
			GraphImmutableEnvironment environment) {
		// TODO Auto-generated method stub
		return null;
	}

	// default lowering method returns the default lowering of each component
	// independent of the initial value
	@Override
	public AbstractKnowledgeExpression express() {
		// TODO switch to Set Monad and apply map
		HashSet<KnowledgeExpression> expressionComponents = new HashSet<KnowledgeExpression>();
		for (KnowledgeAsset asset : components()) {
			// TODO check if the asset is Self-Lowering
			// if not, use the standard Lifter
			expressionComponents
					.add(((AbstractKnowledgeAsset) asset).express());
		}
		return new StructuredKnowledgeExpressionLI(this.environment,
				expressionComponents);
	}

}
