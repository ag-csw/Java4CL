package cl2a;


public abstract class CLTextSequence extends CLExpressionLike {

	public CLTextSequence() {
		super();
	}

	public abstract Iterable<CLText> texts();
	
	public abstract int length();

	public abstract CLTextSequence concat(CLTextSequence intexts);

}