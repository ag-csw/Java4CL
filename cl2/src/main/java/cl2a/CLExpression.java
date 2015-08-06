package cl2a;

import api4kbj.BasicKnowledgeExpression;
import cl2i.CLCommentable;

public abstract class CLExpression extends CLExpressionLike implements
		CLKnowledgeResource, BasicKnowledgeExpression, CLCommentable {

	private CLCommentSequence comments;

	
	public CLExpression(
			final CLCommentSequence comments){
		this.comments = comments;
		
	}
	
	@Override
	public CLCommentSequence comments() {
		return comments;
	}


}
