package cl2;

/**
 * 
 * @author ralph
 */
public abstract class CLDiscourseStatement extends CLStatement {

	private CLTermSequence args;

	/**
	 * 
	 */
	public CLDiscourseStatement(CLTermSequence args) {
		this.args = args;
	}
	
	public CLTermSequence args() {
		return args;
	}

}
