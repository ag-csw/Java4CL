/**
 * 
 */
package cl2;

import java.util.function.Function;

import cl2a.CLCommentSet;
import cl2a.CLTerm;
import cl2a.CLTermSequence;
import cl2a.CLText;
import cl2a.CLExpressionSet;

/**
 * @author tara
 *
 */
public class CLTextConstruction
		extends CLText {
    // private CLCommentSet comments;
	private final CLExpressionSet expressions;

	
    public CLTextConstruction(
			CLCommentSet comments,
			CLExpressionSet expressions) {
		super(comments);
		this.expressions = expressions;
	}




	public CLExpressionSet expressions() {
		return expressions;
	}


	@Override
	public CLTextConstruction insertComments(CLCommentSet incomments) {
		return new CLTextConstruction( comments().concat(incomments), 
				expressions());
	}

	@Override
	public CLTextConstruction copy() {
		return new CLTextConstruction(comments().copy(), expressions().copy());
	}

    /**
     * Returns a modified copy derived by applying functions to each of the
     * accessor methods: comments(), expressions().
     * Law: when the passed operators are the identity operators, then the
     * copy is equal to the original.
     * Law: copy is composable.
     * 
     * @param commentsOperator function that modifies a CL comment sequence
     * @param expressionsOperator  function that modifies a CL expression sequence
     * @return modified copy of this CL atomic sentence
     */
	public CLTextConstruction copy(
			final Function<CLCommentSet, ? extends CLCommentSet> commentsOperator,
			final Function<CLExpressionSet, ? extends CLExpressionSet> expressionsOperator
			) {
				return 
						new CLTextConstruction(
								commentsOperator.apply(comments()),
								expressionsOperator.apply(expressions())
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
	            expressions().toString() + "</cl:Construct>";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expressions() == null) ? 0 : expressions().hashCode());
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
		if (expressions() == null) {
			if (other.expressions() != null)
				return false;
		} else if (!expressions().equals(other.expressions()))
			return false;
		if (comments() == null) {
			if (other.comments() != null)
				return false;
		} else if (!comments().equals(other.comments()))
			return false;
		return true;
	}	
}

