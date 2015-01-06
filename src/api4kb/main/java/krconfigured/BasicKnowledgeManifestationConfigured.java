package krconfigured;

import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KnowledgeManifestation;

public interface BasicKnowledgeManifestationConfigured extends KnowledgeManifestation,
		BasicKnowledgeResourceConfigured {

	// getter for dialect
	KRRDialect dialect();

	<T> T build(KRRDialectType<T> dialectType);

}
