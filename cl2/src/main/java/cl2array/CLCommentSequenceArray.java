package cl2array;

import java.util.Arrays;

import cl2.CLComment;
import cl2a.CLCommentSequence;

public class CLCommentSequenceArray extends CLCommentSequence {
	
	private final CLComment[] comments;

	public CLCommentSequenceArray(CLComment... comments) {
		this.comments = comments;
	}

	@Override
	public Iterable<CLComment> args() {
		return Arrays.asList(comments);
	}

	@Override
	public int length(){
		return comments.length;
	}
	
	@Override
	public CLCommentSequence concat(CLCommentSequence incomments) {
		int bLen = incomments.length();
		CLComment[] b= new CLComment[bLen];
		int i = 0;
        for (final CLComment incomment : incomments.args())
        {
            b[i++] = incomment;
        }		
        CLComment[] c = CLArray.concatComments(comments, b);
		return new CLCommentSequenceArray(c);
	}

}
