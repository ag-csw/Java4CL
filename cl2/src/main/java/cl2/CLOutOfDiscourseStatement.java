/**
 * 
 */
package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLDiscourseStatement;
import cl2a.CLTermSequence;


/**
 * @author ralph
 *
 */
public class CLOutOfDiscourseStatement extends CLDiscourseStatement {


	public CLOutOfDiscourseStatement(
			CLCommentSequence comments, 
			CLTermSequence args) {
		super(comments, args);
	}


	@Override
	public CLOutOfDiscourseStatement insertComments(CLCommentSequence incomments) {
		return new  CLOutOfDiscourseStatement( comments().concat(incomments), 
				args());
	}


}
