package cl2;

import functional.Option;

public class CLImportationExpression extends CLExpression {
	public CLImportationExpression(Option<CLCommentExpression> commment,
			CLPrefixExpression[] prefixes, CLNameExpression name) {
		super();
		this.commment = commment;
		this.prefixes = prefixes;
		this.name = name;
	}

	private Option<CLCommentExpression> commment;
	private CLPrefixExpression[] prefixes;
	private CLNameExpression name;

	// getter for comment
	Option<CLCommentExpression> comment() {
		return commment;
	}

	// getter for prefixes
	CLPrefixExpression[] prefixes(){
		return prefixes;
	}

	// getter for name
	CLNameExpression name(){
		return name;
	}

}
