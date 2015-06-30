/**
 * 
 */
package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLPrefixSequence;
import cl2a.CLSentence;
import cl2a.CLSequenceMarkerSequence;
import cl2a.CLStatement;

/**
 * @author ralph
 *
 */
public class CLSchema extends CLStatement {

	private CLSequenceMarkerSequence bindings;
	private CLSentence body;

	public CLSchema(
			final CLPrefixSequence prefixes, 
			final CLCommentSequence comments,
			final CLSequenceMarkerSequence bindings,
			final CLSentence body) {
		super(prefixes, comments);
		this.bindings = bindings;
		this.body = body;
	}

	/**
	 * @return the declarations
	 */
	public CLSequenceMarkerSequence declarations() {
		return bindings;
	}

	/**
	 * @return the body
	 */
	public CLSentence body() {
		return body;
	}
	
	@Override
	public CLSchema insertComments(final CLCommentSequence incomments) {
		return new CLSchema( prefixes(), comments().concat(incomments), 
				bindings, body);
	}


	@Override
	public CLSchema insertPrefixes(final CLPrefixSequence inprefixes) {
		return new CLSchema( prefixes().concat(inprefixes),
				comments(), bindings, body);
	}



}
