/**
 * 
 */
package cl2;

import java.util.function.Function;

import cl2a.CLQuantifiedSentence;
import cl2a.CLCommentSet;
import cl2a.CLBindingSet;
import cl2a.CLSentence;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class CLExistential extends CLQuantifiedSentence {

	//private CLCommentSet comments;
	//private CLBindingSet bindings;
	//private CLSentence body;
	final static Logger logger = LoggerFactory.getLogger(CLExistential.class);
	
	public CLExistential(
			final CLCommentSet comments,
			final CLBindingSet bindings,
			final CLSentence body) {
		super(comments, bindings, body);
		logger.info("Construction of CLExistential complete.");
		logger.debug("CLExistential comments: " + comments);
		logger.debug("CLExistential bindings: " + bindings);
		logger.debug("CLExistential body: " + body);
	}

	@Override
	public CLExistential insertComments(final CLCommentSet incomments) {
		logger.info("Insert method of CLExistential called.");
		logger.debug("Comments to be inserted in CLExistential: " + incomments);
		return new CLExistential( comments().concat(incomments), 
				bindings(), body());
	}

	@Override
	public CLExistential copy() {
		logger.info("Empty copy method of CLExistential called.");
		logger.debug("Existential to be copied: " + this);
		return new CLExistential(comments().copy(), bindings().copy(), body().copy());
	}

    /**
     * Returns a modified copy derived by applying functions to each of the
     * accessor methods: comments(), bindings(), body().
     * Law: when the passed operators are the identity operators, then the
     * copy is equal to the original.
     * Law: copy is composable.
     * 
     * @param commentsOperator function that modifies a CL comment sequence
     * @param bindingsOperator function that modifies a CL sentence sequence
     * @param bodyOperator function that modifies a CL sentence sequence
     * @return modified copy of this CL existential
     */
	@Override
	public CLExistential copy(
			final Function<CLCommentSet, ? extends CLCommentSet> commentsOperator,
			final Function<CLBindingSet, ? extends CLBindingSet> bindingsOperator,
			final Function<CLSentence, ? extends CLSentence> bodyOperator
			) {
		logger.info("Lens copy method of CLExistential called.");
				return 
						new CLExistential(
								commentsOperator.apply(comments()),
								bindingsOperator.apply(bindings()),
								bodyOperator.apply(body())
								);
		
	}

	/**
     * Returns the XCL2 sour syntax for the existential quantification sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Exists>" + 
	            comments().toString() +
	            bindings().toString() +
	            body().toString() + "</cl:Exists>";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bindings() == null) ? 0 : bindings().hashCode());
		result = prime * result + ((body() == null) ? 0 : body().hashCode());
		result = prime * result + ((comments() == null) ? 0 : comments().hashCode());
		return result;
	}

	public boolean canEqual(Object other) {
        return (other instanceof CLExistential);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLExistential))
			return false;
		CLExistential other = (CLExistential) obj;
		if (!other.canEqual(this))
			return false;
		if (bindings() == null) {
			if (other.bindings() != null)
				return false;
		} else if (!bindings().equals(other.bindings()))
			return false;
		if (body() == null) {
			if (other.body() != null)
				return false;
		} else if (!body().equals(other.body()))
			return false;
		if (comments() == null) {
			if (other.comments() != null)
				return false;
		} else if (!comments().equals(other.comments()))
			return false;
		return true;
	}


}
