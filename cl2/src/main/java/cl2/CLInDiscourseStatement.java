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
public class CLInDiscourseStatement extends CLDiscourseStatement {


	public CLInDiscourseStatement(
			CLCommentSet comments, 
			CLTermSequence args) {
		super(comments, args);
	}


	@Override
	public CLInDiscourseStatement insertComments(CLCommentSet incomments) {
		return new  CLInDiscourseStatement( comments().concat(incomments), 
				args());
	}

	@Override
	public CLInDiscourseStatement copy() {
		return new CLInDiscourseStatement(comments(), args());
	}

}
