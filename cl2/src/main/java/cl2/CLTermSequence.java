/**
 * 
 */
package cl2;

import java.util.Arrays;

/**
 * @author ralph
 *
 */
public class CLTermSequence extends CLExpressionLike {

	private CLTermOrSequenceMarker[] args;
	
	/**
	 * 
	 */
	public CLTermSequence(CLTermOrSequenceMarker... args) {
		this.args = args;
	}
	
	Iterable<CLTermOrSequenceMarker> args(){
		return Arrays.asList(args);
	}

}
