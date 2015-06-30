/**
 * 
 */
package cl2;

import cl2a.CLBooleanSentence;
import cl2a.CLCommentSequence;
import cl2a.CLPrefixSequence;
import cl2a.CLSentenceSequence;

/**
 * @author ralph
 *
 */
public class CLConjunction extends CLBooleanSentence {

	private final CLSentenceSequence conjuncts;

	/**
	 * 
	 */
	public CLConjunction(
			final CLPrefixSequence prefixes, 
			final CLCommentSequence comments,
			final CLSentenceSequence conjuncts
			) {
		super(prefixes, comments);
		this.conjuncts = conjuncts;

	}

	/**
	 * @return the left condition
	 */
	public CLSentenceSequence conjuncts() {
		return conjuncts;
	}


	@Override
	public CLConjunction insertComments(final CLCommentSequence incomments) {
		return new CLConjunction( prefixes(), comments().concat(incomments), 
				conjuncts);
	}


	@Override
	public CLConjunction insertPrefixes(final CLPrefixSequence inprefixes) {
		return new CLConjunction( prefixes().concat(inprefixes),
				comments(), conjuncts);
	}

	
	
}
