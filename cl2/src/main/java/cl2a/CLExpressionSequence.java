package cl2a;


public abstract class CLExpressionSequence extends CLExpressionLike {

	public CLExpressionSequence() {
		super();
	}

	public abstract Iterable<? extends CLExpression> args();
	
	public abstract int length();

	public abstract CLExpressionSequence concat(CLExpressionSequence inargs);

}