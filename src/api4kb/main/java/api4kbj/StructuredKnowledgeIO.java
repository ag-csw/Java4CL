package api4kbj;


public interface StructuredKnowledgeIO extends KnowledgeIO,
		StructuredKnowledgeResource<KnowledgeIO> {
	
	// getter for formats
	Iterable<KRRFormat> formats();


}