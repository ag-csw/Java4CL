package cl2a;

import java.util.Collection;


public abstract class CLExpressionSequence extends CLExpressionLike {

	public CLExpressionSequence() {
		super();
	}

	public abstract Collection<? extends CLExpression> args();
	
	public abstract int length();

	public abstract CLExpressionSequence concat(CLExpressionSequence inargs);

}