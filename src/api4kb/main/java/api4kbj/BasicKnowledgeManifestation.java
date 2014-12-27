package api4kbj;

public interface BasicKnowledgeManifestation extends KnowledgeManifestation {

	// getter for dialect
	KRRDialect dialect();

	// getter for wrapped objects
	<T> T value();

}
