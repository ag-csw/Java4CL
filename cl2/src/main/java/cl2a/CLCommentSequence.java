package cl2a;

import cl2.CLComment;



public abstract class CLCommentSequence extends CLExpressionLike {

	public CLCommentSequence() {
		super();
	}

	//TODO rename to comments()
	public abstract Iterable<CLComment> args();
	
	public abstract int length();

	public abstract CLCommentSequence concat(CLCommentSequence incomments);

}