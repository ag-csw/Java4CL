package cl2;

import api4kbj.BasicAtomicKnowledgeResource;
import functional.Option;

public interface CLComment extends CLKnowledgeResource,
		BasicAtomicKnowledgeResource {
	// getter for symbol
	String getSymbol();

	// getter for optional comment
	Option<?> getComment();

}
