package cl2array;

import java.util.Arrays;
import java.util.Collection;

import cl2.CLStringComment;
import cl2a.CLCommentSequence;

public class CLCommentSequenceArray extends CLCommentSequence {
	
	private final CLStringComment[] comments;

	public CLCommentSequenceArray(CLStringComment... comments) {
		this.comments = comments;
	}

	@Override
	public Collection<CLStringComment> args() {
		return Arrays.asList(comments);
	}

	@Override
	public int length(){
		return comments.length;
	}
	
	@Override
	public CLCommentSequence concat(CLCommentSequence incomments) {
		int bLen = incomments.length();
		CLStringComment[] b= new CLStringComment[bLen];
		int i = 0;
        for (final CLStringComment incomment : incomments.args())
        {
            b[i++] = incomment;
        }		
        CLStringComment[] c = CLArray.concatComments(comments, b);
		return new CLCommentSequenceArray(c);
	}

}
