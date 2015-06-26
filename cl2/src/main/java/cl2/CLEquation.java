package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLPrefixSequence;
import cl2a.CLSimpleSentence;
import cl2a.CLTerm;


/**
 * @author ralph
 *
 */
public class CLEquation extends CLSimpleSentence {

	private final CLTerm left;
	private final CLTerm right;

	public CLEquation(CLPrefixSequence prefixes, CLCommentSequence comments,
			CLTerm left, CLTerm right) {
		super(prefixes, comments);
		this.left = left;
		this.right = right;
	}

	
	@Override
	public CLEquation insertComments(CLCommentSequence incomments) {
		return new CLEquation( prefixes(), comments().concat(incomments), 
				left, right);
	}

	@Override
	public CLEquation insertPrefixes(CLPrefixSequence inprefixes) {
		return new CLEquation( prefixes().concat(inprefixes),
				comments(), left, right);
	}


}
