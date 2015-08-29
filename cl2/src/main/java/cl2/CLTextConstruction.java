/**
 * 
 */
package cl2;

import cl2a.CLCommentSet;
import cl2a.CLText;
import cl2a.CLExpressionSequence;

/**
 * @author tara
 *
 */
public class CLTextConstruction
		extends CLText {

    public CLTextConstruction(
			CLCommentSet comments,
			CLExpressionSequence args) {
		super(comments);
		this.args = args;
	}


	private final CLExpressionSequence args;


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
     * Returns the XCL2 sour syntax for the conjunction sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Construct>" + 
	            comments().toString() +
	            args.toString() + "</cl:Construct>";
	}	
}

