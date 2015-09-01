/**
 * 
 */
package cl2;

import java.util.function.Function;

import cl2a.CLBooleanSentence;
import cl2a.CLCommentSet;
import cl2a.CLSentence;
import cl2a.CLSentenceSet;
import cl2array.CLSentenceSetArray;

public class CLBiconditional extends CLBooleanSentence {

	// private CLCommentSet comments;
	private final CLSentenceSet args;

	public CLBiconditional( 
			final CLCommentSet comments, 
			final CLSentenceSet args){
		super(comments);
		if (args==null)
			throw new NullPointerException("Arguments of a CLBiconditional should not be null.");
		if((args.length()<1) || (args.length()>2))
			throw new IllegalArgumentException(
					"The size of the sentence sequence of a CLBiconditional should be 1 or 2 ");
		this.args = args;
	}

	public CLBiconditional(
			final CLCommentSet comments,
			final CLSentence left,
			final CLSentence right
			) {
		this(comments, new CLSentenceSetArray(left, right));
	}


	/**
	 * @return the sentences
	 */
	public CLSentenceSet args() {
		return args;
	}


	@Override
	public CLBiconditional insertComments(final CLCommentSet incomments) {
		return new CLBiconditional( comments().concat(incomments), 
				args());
	}

	@Override
	public CLBiconditional copy() {
		return new CLBiconditional(comments().copy(), args().copy());
	}

    /**
     * Returns a modified copy derived by applying functions to each of the
     * accessor methods: comments(), args().
     * Law: when the passed operators are the identity operators, then the
     * copy is equal to the original.
     * Law: copy is composable.
     * 
     * @param commentsOperator function that modifies a CL comment sequence
     * @param argsOperator function that modifies a CL sentence sequence
     * @return modified copy of this CL biconditional
     */
	public CLBiconditional copy(
			final Function<CLCommentSet, ? extends CLCommentSet> commentsOperator,
			final Function<CLSentenceSet, ? extends CLSentenceSet> argsOperator
			) {
				return 
						new CLBiconditional(
								commentsOperator.apply(comments()),
								argsOperator.apply(args())
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
	            args().toString() + "</cl:Biconditional>";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments() == null) ? 0 : comments().hashCode());
		result = prime * result + ((args() == null) ? 0 : args().hashCode());
		return result;
	}

	public boolean canEqual(Object other) {
        return (other instanceof CLBiconditional);
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
		if (args() == null) {
			if (other.args() != null)
				return false;
		} else if (!args().equals(other.args()))
			return false;
		return true;
	}

	
}
