/**
 * 
 */
package cl2array;

import java.util.Arrays;

import cl2a.CLExpressionLike;
import cl2a.CLTermOrSequenceMarker;

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
