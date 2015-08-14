package cl2a;

import java.util.Collection;



public abstract class CLTermSequence extends CLExpressionLike {

	public CLTermSequence() {
		super();
	}

	public abstract Collection<? extends CLTermOrSequenceMarker> args();

	public abstract int length();

	public abstract CLTermSequence concat(CLTermSequence inargs);

}