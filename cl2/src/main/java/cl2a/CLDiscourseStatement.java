package cl2a;

import cl2array.CLTermSequence;

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
