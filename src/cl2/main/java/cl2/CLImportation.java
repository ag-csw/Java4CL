package cl2;

import api4kbj.Option;

public interface CLImportation extends CLKnowledgeResource {
	// getter for comment
	Option<CLComment> comment();

	// getter for prefixes
	CLPrefix[] prefixes();

	// getter for name
	CLName name();

}
