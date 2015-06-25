package cl2;

import api4kbj.BasicKnowledgeExpression;

public abstract class CLExpression extends CLExpressionLike implements
		CLKnowledgeResource, BasicKnowledgeExpression, CLCommentable {

	abstract public CLExpression insertComments(
			CLCommentExpression... comments);


}
