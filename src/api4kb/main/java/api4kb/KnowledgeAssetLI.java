package api4kb;


public class KnowledgeAssetLI extends AbstractKnowledgeAsset {

	// lazy lifting constructor
	KnowledgeAssetLI(AbstractKnowledgeExpression expression,
			GraphImmutableEnvironment env) {
		super(expression, env);
	}

	// lazy lifting static factory method
	public static KnowledgeAssetLI lazyNewInstance(
			AbstractKnowledgeExpression expression, GraphImmutableEnvironment env) {
		return new KnowledgeAssetLI(expression, env);
	}


}
