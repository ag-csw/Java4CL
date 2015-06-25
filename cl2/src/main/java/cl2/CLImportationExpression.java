package cl2;

import functional.Option;

public abstract class CLImportationExpression extends CLText {

	private CLPrefixExpression[] prefixes;
	private CLName name;


	// getter for prefixes
	CLPrefixExpression[] prefixes(){
		return prefixes;
	}

	// getter for name
	CLName name(){
		return name;
	}


}
