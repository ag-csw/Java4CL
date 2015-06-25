package cl2a;

import cl2.CLName;

public abstract class CLImportationExpression extends CLText {

	public CLImportationExpression(CLPrefixSequence prefixes,
			CLCommentSequence comments, CLName name) {
		super(prefixes, comments);
		this.name = name;
	}

	private CLName name;

	// getter for name
	CLName name(){
		return name;
	}


}
