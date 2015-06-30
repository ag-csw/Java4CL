/**
 * 
 */
package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLDiscourseStatement;
import cl2a.CLPrefixSequence;
import cl2a.CLTermSequence;


/**
 * @author ralph
 *
 */
public class CLOutOfDiscourseStatement extends CLDiscourseStatement {


	public CLOutOfDiscourseStatement(
			CLPrefixSequence prefixes,
			CLCommentSequence comments, 
			CLTermSequence args) {
		super(prefixes, comments, args);
	}


	@Override
	public CLOutOfDiscourseStatement insertComments(CLCommentSequence incomments) {
		return new  CLOutOfDiscourseStatement( prefixes(), comments().concat(incomments), 
				args());
	}

	@Override
	public CLOutOfDiscourseStatement insertPrefixes(CLPrefixSequence inprefixes) {
		return new  CLOutOfDiscourseStatement( prefixes().concat(inprefixes),
				comments(), args());
	}


}
