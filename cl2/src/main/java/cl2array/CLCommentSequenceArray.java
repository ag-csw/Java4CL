package cl2array;

import java.util.Arrays;

import cl2.CL;
import cl2.CLCommentExpression;
import cl2a.CLCommentSequence;

public class CLCommentSequenceArray extends CLCommentSequence {
	
	private final CLCommentExpression[] comments;

	public CLCommentSequenceArray(CLCommentExpression... comments) {
		this.comments = comments;
	}

	@Override
	public Iterable<CLCommentExpression> args() {
		return Arrays.asList(comments);
	}

	@Override
	public int length(){
		return comments.length;
	}
	
	@Override
	public CLCommentSequence concat(CLCommentSequence incomments) {
		int bLen = incomments.length();
		CLCommentExpression[] b= new CLCommentExpression[bLen];
		int i = 0;
        for (final CLCommentExpression incomment : incomments.args())
        {
            b[i] = incomment;
        }		
        CLCommentExpression[] c = CL.concatComments(comments, b);
		return new CLCommentSequenceArray(c);
	}

}
