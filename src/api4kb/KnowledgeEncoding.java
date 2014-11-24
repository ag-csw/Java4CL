package api4kb;

public interface KnowledgeEncoding<T, S> extends KnowledgeResource {
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.ENCODING;
	
	// getter for wrapped data
	// T may be ByteSequence, byte[] or other byte-oriented data structure
	S getValue();

	// clear memoization cache of the decode method
	void clearDecode();
		
	//getter for encoding system
	EncodingSystem<T, S> getEncodingSystem();
	
	//getter for dialect
	KRRDialect<T> getDialect();
	
	// provides a canonical String representation of the Encoding
	String toString();

	// lifting method
	KnowledgeManifestation<T> decode();


}
