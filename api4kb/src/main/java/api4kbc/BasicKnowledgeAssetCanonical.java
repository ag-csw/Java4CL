package api4kbc;

import api4kba.KnowledgeAssetCanonical;
import api4kbj.BasicKnowledgeAsset;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.FocusedLanguageEnvironment;

public class BasicKnowledgeAssetCanonical extends KnowledgeAssetCanonical
		implements BasicKnowledgeAsset {

	public BasicKnowledgeAssetCanonical(
			FocusedLanguageEnvironment environment,
			BasicKnowledgeExpression canonicalExpression) {
		super(environment, canonicalExpression);
	}

	@Override
	public boolean isBasic() {
		return true;
	}

}
