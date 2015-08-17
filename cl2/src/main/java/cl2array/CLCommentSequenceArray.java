package cl2array;

import java.util.Arrays;
import java.util.Collection;

import cl2a.CLComment;
import cl2a.CLCommentSequence;

public class CLCommentSequenceArray extends CLCommentSequence {
	
	private final CLComment[] comments;

	public CLCommentSequenceArray(CLComment... comments) {
		this.comments = comments;
	}

	public <T extends CLComment> CLCommentSequenceArray(Collection<T> comments) {
		this(comments.toArray(new CLComment[0]));
	}

	@Override
	public Collection<? extends CLComment> args() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(comments);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CLCommentSequenceArray other = (CLCommentSequenceArray) obj;
		if (!Arrays.equals(comments, other.comments))
			return false;
		return true;
	}

}
