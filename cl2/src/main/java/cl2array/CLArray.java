package cl2array;

import cl2.CLComment;
import cl2.CLStringPrefix;
import cl2a.CLExpression;
import cl2a.CLTermOrSequenceMarker;

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

	public static CLStringPrefix[] concatPrefixes(
			final CLStringPrefix[] a, final CLStringPrefix[] b) {
		int aLen = a.length;
		int bLen = b.length;
		CLStringPrefix[] c= new CLStringPrefix[aLen+bLen];
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
