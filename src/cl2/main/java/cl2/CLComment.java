package cl2;

import krconfigured.BasicAtomicKnowledgeResourceConfigured;
import functional.Option;

public interface CLComment extends CLKnowledgeResource,
		BasicAtomicKnowledgeResourceConfigured {
	// getter for symbol
	String getSymbol();

	// getter for optional comment
	Option<?> getComment();

}
