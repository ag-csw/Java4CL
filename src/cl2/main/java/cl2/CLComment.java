package cl2;

import api4kbj.Option;

public interface CLComment extends CLKnowledgeResource {
	// getter for symbol
	String getSymbol();

	// getter for optional comment
	Option<?> getComment();

}
