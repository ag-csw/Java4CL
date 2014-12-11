package api4kb;

public abstract class AbstractKnowledgeAsset implements KnowledgeAsset {

	public AbstractKnowledgeAsset() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	// clear memoization cache of the express method for the particular dialect
	public void clearExpress(KRRLanguage lang) {
		// TODO Auto-generated method stub

	}

	@Override
	public GraphImmutableEnvironment getEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	// lowering method accepts a parameter indicating the language
	public AbstractKnowledgeExpression express(KRRLanguage lang)
			throws LanguageIncompatibleException {
		// TODO Auto-generated method stub
		return null;
	}

}
