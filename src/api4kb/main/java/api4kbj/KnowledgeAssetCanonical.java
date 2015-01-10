package api4kbj;

public abstract class KnowledgeAssetCanonical extends AbstractKnowledgeAsset {

	public KnowledgeAssetCanonical(FocusedImmutableEnvironment environment,
			KnowledgeExpression canonicalExpression) {
		super(environment);
		this.canonicalExpression = canonicalExpression;
	}

	private KnowledgeExpression canonicalExpression;

	@Override
	public KnowledgeExpression canonicalExpression() {
		return canonicalExpression;
	}


}