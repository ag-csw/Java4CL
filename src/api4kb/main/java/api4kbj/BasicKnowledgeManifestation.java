package api4kbj;

public interface BasicKnowledgeManifestation extends KnowledgeManifestation,
		BasicKnowledgeResource {

	// getter for dialect
	KRRDialect dialect();

	<T> T build(KRRDialectType<T> dialectType);

}