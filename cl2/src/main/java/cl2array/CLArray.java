package cl2array;

import cl2.CLStringComment;
import cl2a.CLExpression;
import cl2a.CLTermOrSequenceMarker;

public final class CLArray {

	// private constructor to enforce non-instantiability
	private CLArray() {
	}

	
	public static CLStringComment[] concatComments(
			final CLStringComment[] a, final CLStringComment[] b) {
		int aLen = a.length;
		int bLen = b.length;
		CLStringComment[] c= new CLStringComment[aLen+bLen];
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
