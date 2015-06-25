package cl2a;

import cl2.CLCommentExpression;
import cl2i.CLCommentable;
import api4kbj.BasicKnowledgeExpression;

public abstract class CLExpression extends CLExpressionLike implements
		CLKnowledgeResource, BasicKnowledgeExpression, CLCommentable {

	@Override
	abstract public CLExpression insertComments(
			CLCommentExpression... comments);


}
