/**
 * 
 */
package cl2;

import java.util.function.Function;

import api4kbj.KRRLanguage;
import api4kbj.KnowledgeSourceLevel;
import cl2a.CLCommentSet;
import cl2a.CLBindingSet;
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
			final CLCommentSet comments,
			final CLBindingSet bindings,
			final CLSentence body) {
		super(comments, bindings, body);

	}



	@Override
	public CLUniversal insertComments(final CLCommentSet incomments) {
		return new CLUniversal( comments().concat(incomments), 
				bindings(), body());
	}



	@Override
	public CLUniversal copy() {
		return new CLUniversal(comments().copy(), bindings().copy(), body().copy());
	}

	/**
     * Returns the XCL2 sour syntax for the existential quantification sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Forall>" + 
	            comments().toString() +
	            bindings().toString() +
	            body().toString() + "</cl:Forall>";
	}



	@Override
	public boolean isBasic() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public KnowledgeSourceLevel level() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean usesLanguage(KRRLanguage language) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public CLQuantifiedSentence copy(Function<CLCommentSet, ? extends CLCommentSet> commentsOperator,
			Function<CLBindingSet, ? extends CLBindingSet> bindingsOperator,
			Function<CLSentence, ? extends CLSentence> bodyOperator) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
