package cl2;

public interface CLImportation extends CLKnowledgeResource {
	// getter for comment
	CLComment comment();
	// getter for prefixes
	CLPrefix[] prefixes();
	// getter for name
	CLName name();

}
