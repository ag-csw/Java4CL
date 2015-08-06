package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLTerm;
import cl2a.CLTermSequence;
import cl2i.CLCommentable;

public class CLFunctionalTerm extends CLTerm implements CLCommentable {
	
	private CLCommentSequence comments;
	private final CLTerm operator;
	private final CLTermSequence args;


	public CLFunctionalTerm(
			final CLCommentSequence comments,
			final CLTerm operator, 
			final CLTermSequence args) {
		this.comments = comments;
		this.operator = operator;
		this.args = args;

	}

	@Override
	public CLCommentSequence comments() {
		return comments;
	}
	
	/**
	 * @return the operator
	 */
	public CLTerm operator() {
		return operator;
	}

	/**
	 * @return the arguments
	 */
	public CLTermSequence args() {
		return args;
	}


	@Override
	public CLFunctionalTerm insertComments(final CLCommentSequence incomments) {
		return new CLFunctionalTerm( comments().concat(incomments), 
				operator, args);
	}

	
	


}
