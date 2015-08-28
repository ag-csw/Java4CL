package cl2a;

import api4kba.AbstractBasicKnowledgeExpression;
import cl2.CL;
import cl2i.CLCommentable;

public abstract class CLExpression extends AbstractBasicKnowledgeExpression implements
		CLKnowledgeResource, CLCommentable {

	private CLCommentSet comments;

	
	public CLExpression(
			final CLCommentSet comments){
		super(CL.LANG);
		if(comments==null)
			throw new NullPointerException("Comments of a CLExpression should not be null.");
		this.comments = comments;
		
	}
	
	@Override
	public CLCommentSet comments() {
		return comments;
	}


}
