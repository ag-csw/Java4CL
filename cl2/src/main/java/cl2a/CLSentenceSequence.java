package cl2a;



public abstract class CLSentenceSequence extends CLExpressionLike {

	public CLSentenceSequence() {
		super();
	}

	public abstract Iterable<CLSentence> args();

	public abstract int length();

	public abstract CLSentenceSequence concat(CLSentenceSequence inargs);

}