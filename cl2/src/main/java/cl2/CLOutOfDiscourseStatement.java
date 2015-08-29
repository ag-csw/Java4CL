/**
 * 
 */
package cl2;

import cl2a.CLCommentSet;
import cl2a.CLDiscourseStatement;
import cl2a.CLTermSequence;


/**
 * @author ralph
 *
 */
public class CLOutOfDiscourseStatement extends CLDiscourseStatement {


	public CLOutOfDiscourseStatement(
			CLCommentSet comments, 
			CLTermSequence args) {
		super(comments, args);
	}


	@Override
	public CLOutOfDiscourseStatement insertComments(CLCommentSet incomments) {
		return new  CLOutOfDiscourseStatement( comments().concat(incomments), 
				args());
	}


	@Override
	public CLOutOfDiscourseStatement copy() {
		return new CLOutOfDiscourseStatement(comments().copy(), args().copy());
	}


	/**
     * Returns the XCL2 sour syntax for the out-of-discourse statement, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Out>" + 
	            comments().toString() +
	            args().toString() + "</cl:Out>";
	}

}
