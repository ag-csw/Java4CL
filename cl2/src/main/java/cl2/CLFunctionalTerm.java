package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLTerm;
import cl2a.CLTermSequence;
import cl2i.CLCommentable;

public class CLFunctionalTerm extends CLTerm implements CLCommentable {
	
	private final CLCommentSequence comments;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((args == null) ? 0 : args.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CLFunctionalTerm other = (CLFunctionalTerm) obj;
		if (args == null) {
			if (other.args != null)
				return false;
		} else if (!args.equals(other.args))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		return true;
	}

	
	


}
