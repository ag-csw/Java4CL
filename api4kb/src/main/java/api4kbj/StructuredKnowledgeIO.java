package api4kbj;

public interface StructuredKnowledgeIO extends KnowledgeIO,
		StructuredKnowledgeResource<KnowledgeIO> {

	// getter for store configurations
	Iterable<? extends KRRStoreConfiguration> configs();

}