package cl2;

import cl2a.CLCommentSet;
import cl2a.CLTerm;
import cl2a.CLTermSequence;
import cl2i.CLCommentable;

public class CLFunctionalTerm extends CLTerm implements CLCommentable {
	
	private final CLCommentSet comments;
	private final CLTerm operator;
	private final CLTermSequence args;


	public CLFunctionalTerm(
			final CLCommentSet comments,
			final CLTerm operator, 
			final CLTermSequence args) {
		this.comments = comments;
		this.operator = operator;
		this.args = args;

	}

	@Override
	public CLCommentSet comments() {
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
	public CLFunctionalTerm insertComments(final CLCommentSet incomments) {
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

    public boolean canEqual(Object other) {
        return (other instanceof CLFunctionalTerm);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLFunctionalTerm))
			return false;
		CLFunctionalTerm other = (CLFunctionalTerm) obj;
		if (!other.canEqual(this))
			return false;
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
