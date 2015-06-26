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
	public CLDiscourseStatement(
			final CLPrefixSequence prefixes, 
			final CLCommentSequence comments, 
			final CLTermSequence args) {
		super(prefixes, comments);
		this.args = args;
	}
	
	public CLTermSequence args() {
		return args;
	}

}
