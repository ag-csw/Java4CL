package cl2array;

import cl2a.CLComment;
import cl2a.CLSentence;
import cl2a.CLExpression;
import cl2a.CLTermOrSequenceMarker;
import cl2a.CLInterpretableName;

public final class CLArray {

	// private constructor to enforce non-instantiability
	private CLArray() {
	}

	
	public static CLComment[] concatComments(
			final CLComment[] a, final CLComment[] b) {
		int aLen = a.length;
		int bLen = b.length;
		CLComment[] c= new CLComment[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}

	public static CLTermOrSequenceMarker[] concatArgs(
			final CLTermOrSequenceMarker[] a, final CLTermOrSequenceMarker[] b) {
		int aLen = a.length;
		int bLen = b.length;
		CLTermOrSequenceMarker[] c= new CLTermOrSequenceMarker[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}

	public static CLInterpretableName[] concatBindings(
			final CLInterpretableName[] a, final CLInterpretableName[] b) {
		int aLen = a.length;
		int bLen = b.length;
		CLInterpretableName[] c= new CLInterpretableName[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}

	public static CLSentence[] concatSentences(
			final CLSentence[] a, final CLSentence[] b) {
		int aLen = a.length;
		int bLen = b.length;
		CLSentence[] c= new CLSentence[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}

	public static CLExpression[] concatExpressions(
			final CLExpression[] a, final CLExpression[] b) {
		int aLen = a.length;
		int bLen = b.length;
		CLExpression[] c= new CLExpression[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}
	
}
