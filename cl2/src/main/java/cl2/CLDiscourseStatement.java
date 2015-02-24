package cl2;

/**
 * 
 * @author ralph
 */
public abstract class CLDiscourseStatement {

	private CLTerm[] terms;

	/**
	 * 
	 */
	public CLDiscourseStatement(CLTerm[] terms) {
		this.terms = terms;
	}
	
	public CLTerm[] terms() {
		return terms;
	}

}
