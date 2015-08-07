package cl2a;


/**
 * 
 * @author ralph
 */
public abstract class CLDiscourseStatement extends CLStatement {

	private CLTermSequence args;

    /**
     * 
     * @param comments
     * @param args
     */
	public CLDiscourseStatement(
			final CLCommentSequence comments, 
			final CLTermSequence args) {
		super(comments);
		this.args = args;
	}
	
	public CLTermSequence args() {
		return args;
	}

}
