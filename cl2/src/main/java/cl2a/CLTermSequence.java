package cl2a;

import java.util.List;



public abstract class CLTermSequence extends CLExpressionLike {
	
	public CLTermSequence() {
		super();
	}

	public abstract List<? extends CLTermOrSequenceMarker> args();

	public abstract int length();

	public abstract CLTermSequence concat(CLTermSequence inargs);

}