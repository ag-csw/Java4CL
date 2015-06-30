package cl2i;

import cl2a.CLCommentSequence;
import cl2a.CLExpressionLike;


public interface CLCommentable {
	
	public CLCommentSequence comments();

	public CLExpressionLike insertComments(CLCommentSequence incomments);
}
