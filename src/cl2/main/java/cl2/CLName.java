package cl2;

public interface CLName extends CLKnowledgeResource {
	// getter for comment
	CLComment comment();
	// getter for prefixes
	CLPrefix[] prefixes();
	// getter for symbol
	String symbol();

}
