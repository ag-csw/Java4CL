package api4kbc;

import api4kba.CanonicalKnowledgeAsset;
import api4kbj.BasicKnowledgeAsset;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.FocusedLanguageEnvironment;

public class CanonicalBasicKnowledgeAsset extends CanonicalKnowledgeAsset
		implements BasicKnowledgeAsset {

	public CanonicalBasicKnowledgeAsset(
			final FocusedLanguageEnvironment environment,
			final BasicKnowledgeExpression canonicalExpression) {
		super(environment, canonicalExpression);
	}

	@Override
	public boolean isBasic() {
		return true;
	}

}
