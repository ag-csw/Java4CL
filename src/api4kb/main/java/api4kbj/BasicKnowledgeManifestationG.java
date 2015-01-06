package api4kbj;

import krconfigured.BasicKnowledgeResourceConfigured;

public interface BasicKnowledgeManifestationG<T> extends
		KnowledgeManifestation, BasicKnowledgeResourceConfigured {

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
