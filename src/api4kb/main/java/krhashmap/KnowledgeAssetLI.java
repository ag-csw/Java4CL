package krhashmap;

import graphenvironment.GraphImmutableEnvironment;
import api4kbj.KRRLanguage;

public class KnowledgeAssetLI extends AbstractKnowledgeAsset {

	// lazy lifting constructor
	KnowledgeAssetLI(AbstractKnowledgeExpression expression,
			GraphImmutableEnvironment env) {
		super(expression, env);
	}

	// lazy lifting static factory method
	public static KnowledgeAssetLI lazyNewInstance(
			AbstractKnowledgeExpression expression,
			GraphImmutableEnvironment env) {
		return new KnowledgeAssetLI(expression, env);
	}

	@Override
	AbstractKnowledgeExpression newExpression(KRRLanguage lang) {
		// TODO check the initial value first
		AbstractKnowledgeExpression expression = super.mapExpression.values()
				.iterator().next();
		return (AbstractKnowledgeExpression) environment().apply(expression,
				lang);
	}

}
