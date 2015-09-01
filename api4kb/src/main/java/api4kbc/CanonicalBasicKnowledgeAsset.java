package api4kbc;

import api4kba.CanonicalKnowledgeAsset;
import api4kbj.BasicKnowledgeAsset;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.FocusedLanguageEnvironment;
import api4kbj.KnowledgeExpression;

public class CanonicalBasicKnowledgeAsset extends CanonicalKnowledgeAsset
		implements BasicKnowledgeAsset {
	
	public CanonicalBasicKnowledgeAsset(
			final FocusedLanguageEnvironment environment,
			final BasicKnowledgeExpression canonicalExpression) {
		super(environment, canonicalExpression);

	}
	
	@Override
	public BasicKnowledgeExpression canonicalExpression() {
		return (BasicKnowledgeExpression) super.canonicalExpression();
	}

	@Override
	public boolean isBasic() {
		return true;
	}


	@Override
	public boolean conceptualizes(KnowledgeExpression e) {
		BasicKnowledgeExpression ce = canonicalExpression();
		if (ce!=null && ce.equals(e)) {
			return true;
		}
		// TODO implement test based on equivalence relation that defines the
		// asset
		return false;
	}

}
