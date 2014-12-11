package api4kb;

public interface KnowledgeManifestation<T> extends KnowledgeResource {
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.MANIFESTATION;
	
	//getter for wrapped object
	T getValue();
	
	//getter for dialect
	KRRDialectType<T> getDialect();
	
}
