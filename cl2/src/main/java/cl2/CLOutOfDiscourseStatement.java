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
		return new CLOutOfDiscourseStatement(comments(), args());
	}


}
