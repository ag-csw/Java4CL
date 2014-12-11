package cl2;

import api4kb.Option;

public interface CLName extends CLKnowledgeResource {
	// getter for comment
	Option<CLComment> comment();
	// getter for prefixes
	CLPrefix[] prefixes();
	// getter for symbol
	String symbol();

}
