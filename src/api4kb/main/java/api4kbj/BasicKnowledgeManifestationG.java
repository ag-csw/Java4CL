package api4kbj;

public interface BasicKnowledgeManifestationG<T> extends
		KnowledgeManifestation, BasicKnowledgeResource {

	// getter for wrapped object
	T value();

	// getter for dialect
	default KRRDialect dialect() {
		return dialectType().dialect();
	}

	// getter for dialect type
	KRRDialectType<T> dialectType();

	Class<T> type();

}