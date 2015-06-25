package cl2a;


/**
 * 
 * @author ralph
 */
public abstract class CLDiscourseStatement extends CLStatement {

	private CLTermSequence args;

	/**
	 * 
	 */
	public CLDiscourseStatement(CLPrefixSequence prefixes, CLCommentSequence comments, CLTermSequence args2) {
		super(prefixes, comments);
		this.args = args2;
	}
	
	public CLTermSequence args() {
		return args;
	}

}
