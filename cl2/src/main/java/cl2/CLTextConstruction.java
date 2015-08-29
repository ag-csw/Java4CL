/**
 * 
 */
package cl2;

import java.util.function.Function;

import cl2a.CLCommentSet;
import cl2a.CLTerm;
import cl2a.CLTermSequence;
import cl2a.CLText;
import cl2a.CLExpressionSequence;

/**
 * @author tara
 *
 */
public class CLTextConstruction
		extends CLText {
    // private CLCommentSet comments;
	private final CLExpressionSequence args;

	
    public CLTextConstruction(
			CLCommentSet comments,
			CLExpressionSequence args) {
		super(comments);
		this.args = args;
	}




	public CLExpressionSequence expressions() {
		return args;
	}


	@Override
	public CLTextConstruction insertComments(CLCommentSet incomments) {
		return new CLTextConstruction( comments().concat(incomments), 
				args);
	}

	@Override
	public CLTextConstruction copy() {
		return new CLTextConstruction(comments().copy(), args.copy());
	}

    /**
     * Returns a modified copy derived by applying functions to each of the
     * fields: comments, args.
     * Law: when the passed operators are the identity operators, then the
     * copy is equal to the original.
     * Law: copy is composable.
     * 
     * @param commentsOperator function that modifies a CL comment sequence
     * @param argsOperator  function that modifies a CL expression sequence
     * @return modified copy of this CL atomic sentence
     */
	public CLTextConstruction copy(
			final Function<CLCommentSet, ? extends CLCommentSet> commentsOperator,
			final Function<CLExpressionSequence, ? extends CLExpressionSequence> argsOperator
			) {
				return 
						new CLTextConstruction(
								commentsOperator.apply(comments()),
								argsOperator.apply(args)
								);
		
	}

	/**
     * Returns the XCL2 sour syntax for the conjunction sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Construct>" + 
	            comments().toString() +
	            args.toString() + "</cl:Construct>";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((args == null) ? 0 : args.hashCode());
		result = prime * result + ((comments() == null) ? 0 : comments().hashCode());
		return result;
	}

    public boolean canEqual(Object other) {
        return (other instanceof CLTextConstruction);
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLTextConstruction))
			return false;
		CLTextConstruction other = (CLTextConstruction) obj;
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
		return true;
	}	
}

