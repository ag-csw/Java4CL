package api4kb;

public interface Manifestation<T> extends KnowledgeResource {
	// getter for wrapped data
	// T may be String, XML Element or some other Character-oriented data structure
	T getValue();
	String toString();
	// lifting method
	Expression parse();
	

}
