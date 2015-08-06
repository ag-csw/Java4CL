package api4kbj;

import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/BasicKnowledgeManifestation")
public interface BasicKnowledgeManifestation extends KnowledgeManifestation,
		BasicKnowledgeResource {

	/**
	 * Returns the unique dialect of the basic knowledge manifestation.
	 * 
	 * @return the unique dialect of the basic knowledge manifestation.
	 */
	KRRDialect dialect();

	/**
	 * Returns the unique dialect-type environment of the basic knowledge manifestation.
	 * 
	 * @return the unique dialect-type environment of the basic knowledge manifestation.
	 */
	DialectTypeEnvironment environment();

	@Override
	default boolean usesDialect(final KRRDialect dialect) {
		return dialect().equals(dialect);
	}

	@Override
	default boolean usesDialectTypeEnvironment(final DialectTypeEnvironment environment) {
		return environment().equals(environment);
	}


	<T> T build(final KRRDialectType<T> dialectType);
	

}