package api4kbj;

public interface BasicKnowledgeManifestation extends KnowledgeManifestation,
		BasicKnowledgeResource {

	// getter for dialect
	KRRDialect dialect();

	// getter for wrapped objects
	<T> T value();

}
