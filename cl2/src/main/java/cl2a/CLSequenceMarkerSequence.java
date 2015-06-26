package cl2a;

import cl2.CLSequenceMarker;



public abstract class CLSequenceMarkerSequence extends CLExpressionLike {

	public CLSequenceMarkerSequence() {
		super();
	}

	public abstract Iterable<CLSequenceMarker> args();

	public abstract int length();

	public abstract CLSequenceMarkerSequence concat(CLSequenceMarkerSequence inargs);

}