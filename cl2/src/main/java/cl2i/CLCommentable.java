package cl2i;

import cl2.CLCommentExpression;
import cl2a.CLExpressionLike;


public interface CLCommentable {
	
	public Iterable<CLCommentExpression> comments();

	public CLExpressionLike insertComments(CLCommentExpression... incomments);
}
