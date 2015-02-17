package api4kbj;

public interface BasicKnowledgeManifestation extends KnowledgeManifestation,
		BasicKnowledgeResource {

	/**
	 * Returns the unique dialect of the basic knowledge manifestation.
	 * 
	 * @return the language of the basic knowledge expression.
	 */
	KRRDialect dialect();
	
	@Override
	default boolean usesDialect(KRRDialect dialect) {
		return dialect().equals(dialect);
	}

	<T> T build(KRRDialectType<T> dialectType);

}