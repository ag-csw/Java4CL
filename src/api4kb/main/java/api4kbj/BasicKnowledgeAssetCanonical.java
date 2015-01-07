package api4kbj;

public class BasicKnowledgeAssetCanonical extends KnowledgeAssetCanonical
		implements BasicKnowledgeAsset {

	public BasicKnowledgeAssetCanonical(
			FocusedImmutableEnvironment environment,
			KnowledgeExpression canonicalExpression) {
		super(environment, canonicalExpression);
	}

	@Override
	public boolean isBasic() {
		return true;
	}

}
