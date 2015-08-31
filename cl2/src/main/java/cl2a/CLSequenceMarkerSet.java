package cl2a;


public abstract class CLSequenceMarkerSet extends CLExpressionLike {

	public CLSequenceMarkerSet() {
		super();
	}

	public abstract Iterable<CLSequenceMarker> args();

	public abstract int length();

	public abstract CLSequenceMarkerSet concat(CLSequenceMarkerSet inargs);

	/**
     * Returns the XCL2 sour syntax for the sentence sequence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		String result = "";
		for (CLSequenceMarker s : args() ) result = result + s.toString();
		return result;
	}

	@Override
	public abstract CLSequenceMarkerSet copy();

}