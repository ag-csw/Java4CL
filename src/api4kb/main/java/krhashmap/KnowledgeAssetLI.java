package krhashmap;

import graphenvironment.GraphImmutableEnvironment;
import api4kbj.KRRLanguage;

public class KnowledgeAssetLI extends AbstractKnowledgeAsset {

	// lazy lifting constructor
	KnowledgeAssetLI(AbstractBasicKnowledgeExpression expression,
			GraphImmutableEnvironment env) {
		super(expression, env);
	}

	// lazy lifting static factory method
	public static KnowledgeAssetLI lazyNewInstance(
			AbstractBasicKnowledgeExpression expression,
			GraphImmutableEnvironment env) {
		return new KnowledgeAssetLI(expression, env);
	}

	@Override
	AbstractBasicKnowledgeExpression newExpression(KRRLanguage lang) {
		// TODO check the initial value first
		AbstractBasicKnowledgeExpression expression = super.mapExpression.values()
				.iterator().next();
		return (AbstractBasicKnowledgeExpression) environment().apply(expression,
				lang);
	}

}
