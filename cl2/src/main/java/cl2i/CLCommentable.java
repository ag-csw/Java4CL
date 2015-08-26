package cl2i;

import cl2a.CLCommentSequence;


public interface CLCommentable {
	
	public CLCommentSequence comments();

	public CLCommentable insertComments(CLCommentSequence incomments);
}
