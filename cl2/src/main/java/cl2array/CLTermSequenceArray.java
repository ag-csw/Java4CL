/**
 * 
 */
package cl2array;

import java.util.Arrays;

import cl2a.CLTermOrSequenceMarker;
import cl2a.CLTermSequence;

/**
 * @author ralph
 *
 */
public class CLTermSequenceArray extends CLTermSequence {

	CLTermOrSequenceMarker[] args;
	
	/**
	 * 
	 */
	public CLTermSequenceArray(CLTermOrSequenceMarker... args) {
		this.args = args;
	}


	@Override
	public Iterable<CLTermOrSequenceMarker> args() {
		return Arrays.asList(args);
	}

}
