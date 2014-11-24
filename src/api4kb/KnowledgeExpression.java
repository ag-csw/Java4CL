package api4kb;

public interface KnowledgeExpression extends KnowledgeResource {
	
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.EXPRESSION;
	
	// clear memoization cache of the manifest method for the particular dialect
	void clearManifest(KRRDialect<?> dialect);
	
	// clear memoization cache of the conceptualize method for the particular environment
	void clearConceptualize(ImmutableEnvironment environment);
	
	//getter for language
	KRRLanguage getLanguage();

	// provides a canonical String serialization of the Expression based on a
	// preferred Manifestation type
	// should give the same output as chaining the manifest method (called on the
	// preferred dialect and the default configuration) with the 
	// toString method of the manifestation.
	@Override
	String toString();

	// default lowering method accepts a parameter indicating the dialect
	// with generic T for the format (e.g. String, XML Element)
	<T> KnowledgeManifestation<?> manifest( KRRDialect<T> dialect) 
			throws DialectIncompatibleException;
	
	
	// lifting method
	KnowledgeAsset conceptualize( ImmutableEnvironment e);

}
