package cl2;

import api4kbj.Option;

public interface CLName extends CLKnowledgeResource {
	// getter for comment
	Option<CLComment> comment();

	// getter for prefixes
	CLPrefix[] prefixes();

	// getter for symbol
	String symbol();

}
