/**
 * 
 */
package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLSimpleSentence;
import cl2a.CLTerm;
import cl2a.CLTermSequence;

/**
 * @author ralph
 *
 */
public class CLAtomicSentence extends CLSimpleSentence {

	private final CLTerm operator;
	private final CLTermSequence args;

	/**
	 * 
	 */
	public CLAtomicSentence(
			final CLCommentSequence comments,
			final CLTerm operator, 
			final CLTermSequence args) {
		super(comments);
		if(operator!=null)
			this.operator = operator;
			else
				throw new NullPointerException("Symbol of a CLStringInterpretableName should not be null.");
		if(args!=null)
			this.args = args;
			else
				throw new NullPointerException("Symbol of a CLStringInterpretableName should not be null.");

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
	public CLAtomicSentence insertComments(final CLCommentSequence incomments) {
		return new CLAtomicSentence(comments().concat(incomments), 
				operator, args);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((args == null) ? 0 : args.hashCode());
		result = prime * result + ((comments() == null) ? 0 : comments().hashCode());
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
		CLAtomicSentence other = (CLAtomicSentence) obj;
		if (args == null) {
			if (other.args != null)
				return false;
		} else if (!args.equals(other.args))
			return false;
		if (comments() == null) {
			if (other.comments() != null)
				return false;
		} else if (!comments().equals(other.comments()))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		return true;
	}

	
	
}
