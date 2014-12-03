package api4kb;

public interface KnowledgeAsset extends KnowledgeResource {
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.ASSET;

	// clear memoization cache of the express method for the particular dialect
	void clearExpress(KRRLanguage lang);

	//getter for environment
	ImmutableEnvironment getEnvironment();

	@Override
	String toString();

	// lowering method accepts a parameter indicating the language
	KnowledgeExpression express( KRRLanguage lang) 
			throws LanguageIncompatibleException;

}
