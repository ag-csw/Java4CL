package cl2a;

import cl2.CLPrefixExpression;



public abstract class CLPrefixSequence extends CLExpressionLike {

	public CLPrefixSequence() {
		super();
	}

	public abstract Iterable<CLPrefixExpression> args();
	
	public abstract int length();

	public abstract CLPrefixSequence concat(CLPrefixSequence inprefixes);

}