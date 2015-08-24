/**
 * 
 */
package cl2;

import java.util.function.Function;
import java.util.function.BiFunction;

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

    /**
     * Returns a modified copy derived by applying functions to each of the
     * fields: comments, operator, args.
     * Law: when the passed operators are the identity operators, then the
     * copy is equal to the original.
     * Law: copy is composable.
     * 
     * @param commentsOperator function that modifies a CL comment sequence
     * @param operatorOperator function that modifies a CL term
     * @param argsOperator  function that modifies a CL term sequence
     * @return modified copy of this CL atomic sentence
     */
	public CLAtomicSentence copy(
			final Function<CLCommentSequence, ? extends CLCommentSequence> commentsOperator,
			final Function<CLTerm, ? extends CLTerm> operatorOperator,
			final Function<CLTermSequence, ? extends CLTermSequence> argsOperator
			) {
				return 
						new CLAtomicSentence(
								commentsOperator.apply(comments()),
								operatorOperator.apply(operator),
								argsOperator.apply(args)
								);
		
	}

	@Override
	public CLAtomicSentence insertComments(final CLCommentSequence incomments) {
		BiFunction<CLCommentSequence, CLCommentSequence, CLCommentSequence> F = CLCommentSequence::concat;
		return copy(
				s -> F.apply(s,incomments), 
				s -> s, 
				s -> s);
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
