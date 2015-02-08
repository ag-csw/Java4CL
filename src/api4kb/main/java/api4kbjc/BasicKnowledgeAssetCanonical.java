package api4kbjc;

import api4kbj.BasicKnowledgeAsset;
import api4kbj.FocusedImmutableLanguageEnvironment;
import api4kbj.KnowledgeExpression;

public class BasicKnowledgeAssetCanonical extends KnowledgeAssetCanonical
		implements BasicKnowledgeAsset {

	public BasicKnowledgeAssetCanonical(
			FocusedImmutableLanguageEnvironment environment,
			KnowledgeExpression canonicalExpression) {
		super(environment, canonicalExpression);
	}

	@Override
	public boolean isBasic() {
		return true;
	}

}
