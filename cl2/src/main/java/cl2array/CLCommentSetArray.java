package cl2array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cl2a.CLComment;
import cl2a.CLCommentSet;

public class CLCommentSetArray extends CLCommentSet {
	
	private final CLComment[] comments;

	public CLCommentSetArray(CLComment... comments) {
		super(comments);
		this.comments = comments;
	}

	public <T extends CLComment> CLCommentSetArray(Set<T> comments) {
		this(comments.toArray(new CLComment[comments.size()]));
	}

	@Override
	public Set<? extends CLComment> args() {
		return new HashSet<CLComment>(Arrays.asList(comments));
	}

	@Override
	public int length(){
		return comments.length;
	}
	
	@Override
	public CLCommentSet concat(CLCommentSet incomments) {
		int bLen = incomments.length();
		CLComment[] b= new CLComment[bLen];
		int i = 0;
        for (final CLComment incomment : incomments.args())
        {
            b[i++] = incomment;
        }		
        CLComment[] c = CLArray.concatComments(comments, b);
		return new CLCommentSetArray(c);
	}

	@Override
	public CLCommentSet copy() {
		return new CLCommentSetArray(CLArray.copyComments(comments));
	}

	
}
