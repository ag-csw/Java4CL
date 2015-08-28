package cl2a;

import java.util.Collection;



public abstract class CLSentenceSequence extends CLExpressionLike {

	public CLSentenceSequence() {
		super();
	}

	public abstract Collection<? extends CLSentence> args();

	public abstract int length();

	public abstract CLSentenceSequence concat(CLSentenceSequence inargs);

	/**
     * Returns the XCL2 sour syntax for the sentence sequence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		String result = "";
		for (CLSentence s : args() ) result = result + s.toString();
		return result;
	}
	
}