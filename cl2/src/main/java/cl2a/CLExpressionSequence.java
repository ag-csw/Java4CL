package cl2a;

import java.util.Collection;


public abstract class CLExpressionSequence extends CLExpressionLike {

	public CLExpressionSequence() {
		super();
	}

	public abstract Collection<? extends CLExpression> args();
	
	public abstract int length();

	public abstract CLExpressionSequence concat(CLExpressionSequence inargs);

	@Override
	public abstract CLExpressionSequence copy();

	/**
     * Returns the XCL2 sour syntax for the expression sequence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		String result = "";
		for (CLExpression s : args() ) result = result + s.toString();
		return result;
	}
		
}