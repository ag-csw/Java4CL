package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLSimpleSentence;
import cl2a.CLTerm;


/**
 * @author ralph
 *
 */
public class CLEquation extends CLSimpleSentence {

	private final CLTerm left;
	private final CLTerm right;

	public CLEquation(CLCommentSequence comments,
			CLTerm left, CLTerm right) {
		super(comments);
		this.left = left;
		this.right = right;
	}

	
	@Override
	public CLEquation insertComments(CLCommentSequence incomments) {
		return new CLEquation(comments().concat(incomments), 
				left, right);
	}


}
