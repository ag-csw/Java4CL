package cl2;

import functional.Option;

public interface CLComment extends CLKnowledgeResource {
	// getter for symbol
	String getSymbol();

	// getter for optional comment
	Option<?> getComment();

}
