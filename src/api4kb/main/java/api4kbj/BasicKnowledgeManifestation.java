package api4kbj;

import krconfigured.BasicKnowledgeResourceConfigured;

public interface BasicKnowledgeManifestation extends KnowledgeManifestation,
		BasicKnowledgeResourceConfigured {

	// getter for dialect
	KRRDialect dialect();

	<T> T build(KRRDialectType<T> dialectType);

}
