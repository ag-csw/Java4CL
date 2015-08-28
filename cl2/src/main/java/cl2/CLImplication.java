/**
 * 
 */
package cl2;

import cl2a.CLBooleanSentence;
import cl2a.CLCommentSet;
import cl2a.CLSentence;

/**
 * @author ralph
 *
 */
public class CLImplication extends CLBooleanSentence {

	private final CLSentence antecedent;
	private final CLSentence consequent;

	/**
	 * 
	 */
	public CLImplication(
			final CLCommentSet comments,
			final CLSentence antecedent,
			final CLSentence consequent
			) {
		super(comments);
		this.antecedent = antecedent;
		this.consequent = consequent;

	}

	/**
	 * @return the antecedent
	 */
	public CLSentence antecedent() {
		return antecedent;
	}

	/**
	 * @return the consequent
	 */
	public CLSentence consequent() {
		return consequent;
	}


	@Override
	public CLImplication insertComments(final CLCommentSet incomments) {
		return new CLImplication( comments().concat(incomments), 
				antecedent, consequent);
	}

	@Override
	public CLImplication copy() {
		return new CLImplication(comments(), antecedent, consequent);
	}

	/**
     * Returns the XCL2 sour syntax for the implication sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Implies>" + 
	            comments().toString() +
	            antecedent.toString() +
	            consequent.toString() + "</cl:Implies>";
	}
	
}
