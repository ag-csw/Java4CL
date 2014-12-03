package api4kb;

public interface KnowledgeManifestation<T> extends KnowledgeResource {
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.MANIFESTATION;
	
	// getter for wrapped data
	// T may be String, XML Element or some other Character-oriented data structure
	T getValue();

	// clear memoization cache of the manifest method for the particular dialect
	void clearEncode(EncodingSystem<T, ?> system);

	// clear memoization cache of the parse method
	void clearParse();
	
	//getter for dialect
	KRRDialect<T> getDialect();
	
	//getter for configuration describing the transformation from the
	// canonical value ( of type T) to the actual value as returned by getValue()
	// The format of the configuration is represented as a wildcard because
	// in general it will depend on the dialect.
	// For example, an XML-based dialect would have an XSLT-based configuration.
	Configuration<?> getConfiguration();
	
	// provides a canonical String representation of the Manifestation based on the
	// default configuration
	String toString();
	

	// lowering method accepts a parameter indicating the encoding system
	// with generic T for the format (e.g. ByteSequence, byte{}, ...)
	<S> KnowledgeEncoding<T, S> encode( EncodingSystem<T, S> system) 
			throws EncodingSystemIncompatibleException;

	// lifting method
	KnowledgeExpression parse();


}
