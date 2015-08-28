/**
 * 
 */
package cl2;

import java.util.function.Function;

import cl2a.CLBooleanSentence;
import cl2a.CLCommentSet;
import cl2a.CLSentence;

/**
 * @author ralph
 *
 */
public class CLBiconditional extends CLBooleanSentence {

	//private CLCommentSet comments;
	private final CLSentence left;
	private final CLSentence right;

	/**
	 * 
	 */
	public CLBiconditional(
			final CLCommentSet comments,
			final CLSentence left,
			final CLSentence right
			) {
		super(comments);
		this.left = left;
		this.right = right;

	}

	/**
	 * @return the left condition
	 */
	public CLSentence left() {
		return left;
	}

	/**
	 * @return the right condition
	 */
	public CLSentence right() {
		return right;
	}


	@Override
	public CLBiconditional insertComments(final CLCommentSet incomments) {
		return new CLBiconditional( comments().concat(incomments), 
				left, right);
	}

	@Override
	public CLBiconditional copy() {
		return new CLBiconditional(comments(), left, right);
	}

    /**
     * Returns a modified copy derived by applying functions to each of the
     * fields: comments, left, right.
     * Law: when the passed operators are the identity operators, then the
     * copy is equal to the original.
     * Law: copy is composable.
     * 
     * @param commentsOperator function that modifies a CL comment sequence
     * @param leftOperator function that modifies a CL sentence
     * @param rightOperator  function that modifies a CL sentence
     * @return modified copy of this CL biconditional
     */
	public CLBiconditional copy(
			final Function<CLCommentSet, ? extends CLCommentSet> commentsOperator,
			final Function<CLSentence, ? extends CLSentence> leftOperator,
			final Function<CLSentence, ? extends CLSentence> rightOperator
			) {
				return 
						new CLBiconditional(
								commentsOperator.apply(comments()),
								leftOperator.apply(left),
								rightOperator.apply(right)
								);
		
	}
	
	/**
     * Returns the XCL2 sour syntax for the biconditional sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Biconditional>" + 
	            comments().toString() +
	            left.toString() +
	            right.toString() + "<\\cl:Biconditional>";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments() == null) ? 0 : comments().hashCode());
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	public boolean canEqual(Object other) {
        return (other instanceof CLAtomicSentence);
    }
    

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLBiconditional))
			return false;
		CLBiconditional other = (CLBiconditional) obj;
		if (!other.canEqual(this))
			return false;
		if (comments() == null) {
			if (other.comments() != null)
				return false;
		} else if (!comments().equals(other.comments()))
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	
}
