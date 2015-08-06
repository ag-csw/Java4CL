package cl2a;

import cl2.CLStringSequenceMarker;



public abstract class CLSequenceMarkerSequence extends CLExpressionLike {

	public CLSequenceMarkerSequence() {
		super();
	}

	public abstract Iterable<CLStringSequenceMarker> args();

	public abstract int length();

	public abstract CLSequenceMarkerSequence concat(CLSequenceMarkerSequence inargs);

}