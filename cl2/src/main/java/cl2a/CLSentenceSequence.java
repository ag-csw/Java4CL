package cl2a;

import java.util.Collection;



public abstract class CLSentenceSequence extends CLExpressionLike {

	public CLSentenceSequence() {
		super();
	}

	public abstract Collection<? extends CLSentence> args();

	public abstract int length();

	public abstract CLSentenceSequence concat(CLSentenceSequence inargs);

}