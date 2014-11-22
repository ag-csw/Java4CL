package api4kb;

public interface Expression extends KnowledgeResource {
	
	// lowering method accepts a parameter indicating the dialect
	// with generic T for the format (e.g. String, XML Element)
	<T> Manifestation<T> manifest( Dialect<T> dialect) throws DialectIncompatibleException;

}
