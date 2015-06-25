package cl2;


public interface CLCommentable {
	
	public Iterable<CLCommentExpression> comments();

	public CLExpressionLike insertComments(CLCommentExpression... incomments);
}
