package cl2i;

import cl2a.CLCommentSet;


public interface CLCommentable {
	
	public CLCommentSet comments();

	public CLCommentable insertComments(CLCommentSet incomments);
}
