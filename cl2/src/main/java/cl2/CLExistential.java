/**
 * 
 */
package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLBindingSequence;
import cl2a.CLPrefixSequence;
import cl2a.CLQuantifiedSentence;
import cl2a.CLSentence;

/**
 * @author ralph
 *
 */
public class CLExistential extends CLQuantifiedSentence {

	/**
	 * 
	 */
	public CLExistential(
			final CLPrefixSequence prefixes, 
			final CLCommentSequence comments,
			final CLBindingSequence declarations,
			final CLSentence body) {
		super(prefixes, comments, declarations, body);

	}



	@Override
	public CLExistential insertComments(final CLCommentSequence incomments) {
		return new CLExistential( prefixes(), comments().concat(incomments), 
				bindings(), body());
	}


	@Override
	public CLExistential insertPrefixes(final CLPrefixSequence inprefixes) {
		return new CLExistential( prefixes().concat(inprefixes),
				comments(), bindings(), body());
	}

	
	
}
