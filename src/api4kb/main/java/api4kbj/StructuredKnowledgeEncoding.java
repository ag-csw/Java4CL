package api4kbj;

public interface StructuredKnowledgeEncoding extends KnowledgeEncoding,
		StructuredKnowledgeResource, Decomposable<KnowledgeEncoding> {

	// getter for formats
	Iterable<KRRFormat> formats();

}
