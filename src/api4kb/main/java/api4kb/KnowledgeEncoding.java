package api4kb;

public interface KnowledgeEncoding<T, S> extends KnowledgeResource {
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.ENCODING;
	
	// getter for wrapped stream
	// S should be some type of stream
	S getValue();
		
	//getter for encoding system
	CodecSystem<T, S> getCodecSystem();
	
	//getter for dialect
	KRRDialectType<T> getDialect();
	

}
