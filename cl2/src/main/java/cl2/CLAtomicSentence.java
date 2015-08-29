/**
 * 
 */
package cl2;

import java.util.function.Function;
import java.util.function.BiFunction;

import cl2a.CLCommentSet;
import cl2a.CLSimpleSentence;
import cl2a.CLTerm;
import cl2a.CLTermSequence;

/**
 * @author ralph
 *
 */
public class CLAtomicSentence extends CLSimpleSentence {

	//private CLCommentSet comments;
	private final CLTerm operator;
	private final CLTermSequence args;

	/**
	 * 
	 */
	public CLAtomicSentence(
			final CLCommentSet comments,
			final CLTerm operator, 
			final CLTermSequence args) {
		super(comments);
		if(operator==null)
			throw new NullPointerException("Operator of a CLAtomicSentence should not be null.");
	    this.operator = operator;
		if(args==null)
			throw new NullPointerException("Argument sequence of a CLAtomicSentence should not be null.");
		this.args = args;

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
	public CLAtomicSentence insertComments(final CLCommentSet incomments) {
		BiFunction<CLCommentSet, CLCommentSet, CLCommentSet> F = CLCommentSet::concat;
		return copy(
				s -> F.apply(s,incomments), 
				s -> s, 
				s -> s);
	}

	@Override
	public CLAtomicSentence copy() {
		return new CLAtomicSentence(comments().copy(), operator.copy(), args.copy());
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
			final Function<CLCommentSet, ? extends CLCommentSet> commentsOperator,
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

	/**
     * Returns the XCL2 sour syntax for the atomic sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Atom>" + 
	            comments().toString() +
	            operator.toString() +
	            args.toString() + "</cl:Atom>";
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


    public boolean canEqual(Object other) {
        return (other instanceof CLAtomicSentence);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLAtomicSentence))
			return false;
		CLAtomicSentence other = (CLAtomicSentence) obj;
		if (!other.canEqual(this))
			return false;
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
