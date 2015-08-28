/**
 * 
 */
package cl2array;

import java.util.Arrays;
import java.util.List;

import cl2a.CLTermOrSequenceMarker;
import cl2a.CLTermSequence;

/**
 * @author ralph
 *
 */
public class CLTermSequenceArray extends CLTermSequence {

	private final CLTermOrSequenceMarker[] args;
	
	/**
	 * 
	 */
	public CLTermSequenceArray(CLTermOrSequenceMarker... args) {
		this.args = args;
	}

	public <T extends CLTermOrSequenceMarker> CLTermSequenceArray(List<T> terms) {
		this(terms.toArray(new CLTermOrSequenceMarker[0]));
	}

	@Override
	public List<? extends CLTermOrSequenceMarker> args() {
		return Arrays.asList(args);
	}

	@Override
	public int length(){
		return args.length;
	}

	@Override
	public CLTermSequenceArray concat(CLTermSequence inargs) {
		int bLen = inargs.length();
		CLTermOrSequenceMarker[] b= new CLTermOrSequenceMarker[bLen];
		int i = 0;
        for (final CLTermOrSequenceMarker inarg : inargs.args())
        {
            b[i++] = inarg;
        }		
        CLTermOrSequenceMarker[] c = CLArray.concatArgs(args, b);
		return new CLTermSequenceArray(c);
	}

	@Override
	public CLTermSequenceArray copy() {
		return new CLTermSequenceArray(args());
	}

}
