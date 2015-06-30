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
public class CLInDiscourseStatement extends CLDiscourseStatement {


	public CLInDiscourseStatement(
			CLPrefixSequence prefixes,
			CLCommentSequence comments, 
			CLTermSequence args) {
		super(prefixes, comments, args);
	}


	@Override
	public CLInDiscourseStatement insertComments(CLCommentSequence incomments) {
		return new  CLInDiscourseStatement( prefixes(), comments().concat(incomments), 
				args());
	}

	@Override
	public CLInDiscourseStatement insertPrefixes(CLPrefixSequence inprefixes) {
		return new  CLInDiscourseStatement( prefixes().concat(inprefixes),
				comments(), args());
	}


}
