/**
 * 
 */
package cl2a;




/**
 * @author ralph
 *
 */
public abstract class CLAtomicSentence extends CLSimpleSentence {

	private CLTerm operator;
	private CLTermSequence args;

	/**
	 * 
	 */
	protected CLAtomicSentence(
			CLCommentSequence comments, CLPrefixSequence prefixes,
			CLTerm operator, CLTermSequence args) {
		super(prefixes, comments);
		this.operator = operator;
		this.args = args;

	}

	/**
	 * @return the operator
	 */
	public CLTerm getOperator() {
		return operator;
	}

	/**
	 * @return the arguments
	 */
	public CLTermSequence getArgs() {
		return args;
	}
	
	
}
