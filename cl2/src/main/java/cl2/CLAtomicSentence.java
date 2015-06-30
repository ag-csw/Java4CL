/**
 * 
 */
package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLPrefixSequence;
import cl2a.CLSimpleSentence;
import cl2a.CLTerm;
import cl2a.CLTermSequence;

/**
 * @author ralph
 *
 */
public class CLAtomicSentence extends CLSimpleSentence {

	private final CLTerm operator;
	private final CLTermSequence args;

	/**
	 * 
	 */
	public CLAtomicSentence(
			final CLPrefixSequence prefixes, 
			final CLCommentSequence comments,
			final CLTerm operator, 
			final CLTermSequence args) {
		super(prefixes, comments);
		this.operator = operator;
		this.args = args;

	}

	/**
	 * @return the operator
	 */
	public CLTerm operator() {
		return operator;
	}

	/**
	 * @return the arguments
	 */
	public CLTermSequence args() {
		return args;
	}


	@Override
	public CLAtomicSentence insertComments(final CLCommentSequence incomments) {
		return new CLAtomicSentence( prefixes(), comments().concat(incomments), 
				operator, args);
	}


	@Override
	public CLAtomicSentence insertPrefixes(final CLPrefixSequence inprefixes) {
		return new CLAtomicSentence( prefixes().concat(inprefixes),
				comments(), operator, args);
	}

	
	
}
