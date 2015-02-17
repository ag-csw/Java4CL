package api4kba;


import api4kbj.FocusedImmutableLanguageEnvironment;
import api4kbj.KnowledgeExpression;

public abstract class KnowledgeAssetCanonical extends AbstractKnowledgeAsset {

	public KnowledgeAssetCanonical(
			FocusedImmutableLanguageEnvironment environment,
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
