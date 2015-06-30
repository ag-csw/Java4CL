package api4kba;


import api4kbj.FocusedLanguageEnvironment;
import api4kbj.KnowledgeExpression;

public abstract class CanonicalKnowledgeAsset extends AbstractKnowledgeAsset {
	

	public CanonicalKnowledgeAsset(
			final FocusedLanguageEnvironment environment,
			final KnowledgeExpression canonicalExpression) {
		super(environment);
		this.canonicalExpression = canonicalExpression;
	}

	final protected KnowledgeExpression canonicalExpression;

	@Override
	public KnowledgeExpression canonicalExpression() {
		return canonicalExpression;
	}
	
	@Override
	public boolean conceptualizes(KnowledgeExpression e) {
		KnowledgeExpression ce = canonicalExpression();
		if (ce.equals(e)) {
			return true;
		}
		// TODO implement test based on equivalence relation that defines the
		// asset
		return false;
	}


}
