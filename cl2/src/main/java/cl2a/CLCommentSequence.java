package cl2a;

import java.util.Collection;



public abstract class CLCommentSequence extends CLExpressionLike {

	public CLCommentSequence() {
		super();
	}

	//TODO rename to comments()
	public abstract Collection<? extends CLComment> args();
	
	public abstract int length();

	public abstract CLCommentSequence concat(CLCommentSequence incomments);
	
}