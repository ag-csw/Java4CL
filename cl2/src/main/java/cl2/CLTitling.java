/**
 * 
 */
package cl2;

/**
 * @author ralph
 *
 */
public class CLTitling extends CLStatement {
	
	private CLName name;
	private CLText text;
	
	/**
	 * @param name
	 * @param text
	 */
	public CLTitling(CLName name, CLText text) {
		this.name = name;
		this.text = text;
	}

	public CLName name() {
		return name;
	}

	public CLText text() {
		return text;
	}



}
