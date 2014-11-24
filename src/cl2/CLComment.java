package cl2;

import api4kb.Option;

public interface CLComment extends CLKnowledgeResource {
	// getter for symbol
	String getSymbol();
	// getter for optional comment
	Option<?> getComment();

}
