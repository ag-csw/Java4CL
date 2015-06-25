package cl2a;

import api4kbj.BasicKnowledgeExpression;
import cl2i.CLCommentable;
import cl2i.CLPrefixable;

public abstract class CLExpression extends CLExpressionLike implements
		CLKnowledgeResource, BasicKnowledgeExpression, CLCommentable, CLPrefixable {

	private CLPrefixSequence prefixes;
	private CLCommentSequence comments;

	
	public CLExpression(CLPrefixSequence prefixes, CLCommentSequence comments){
		this.prefixes = prefixes;
		this.comments = comments;
		
	}
	
	@Override
	public CLCommentSequence comments() {
		return comments;
	}

	@Override
	public CLPrefixSequence prefixes() {
		return prefixes;
	}



}
