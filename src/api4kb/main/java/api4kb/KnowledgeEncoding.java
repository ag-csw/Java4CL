package api4kb;

public interface KnowledgeEncoding<T, S> extends KnowledgeResource {
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.ENCODING;
	
	// getter for wrapped stream
	// S should be some type of stream
	S getValue();
		
	//getter for encoding system
	EncodingSystem<T, S> getEncodingSystem();
	
	//getter for dialect
	KRRDialect<T> getDialect();
	

}
