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
public class CLUniversal extends CLQuantifiedSentence {

	/**
	 * 
	 */
	public CLUniversal(
			final CLPrefixSequence prefixes, 
			final CLCommentSequence comments,
			final CLBindingSequence bindings,
			final CLSentence body) {
		super(prefixes, comments, bindings, body);

	}



	@Override
	public CLUniversal insertComments(final CLCommentSequence incomments) {
		return new CLUniversal( prefixes(), comments().concat(incomments), 
				bindings(), body());
	}


	@Override
	public CLUniversal insertPrefixes(final CLPrefixSequence inprefixes) {
		return new CLUniversal( prefixes().concat(inprefixes),
				comments(), bindings(), body());
	}

	
	
}
