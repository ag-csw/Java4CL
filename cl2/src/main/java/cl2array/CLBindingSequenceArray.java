/**
 * 
 */
package cl2array;

import java.util.Arrays;

import cl2a.CLInterpretableName;
import cl2a.CLBindingSequence;

/**
 * @author ralph
 *
 */
public class CLBindingSequenceArray extends CLBindingSequence {

	private final CLInterpretableName[] args;
	
	/**
	 * 
	 */
	public CLBindingSequenceArray(CLInterpretableName... args) {
		this.args = args;
	}

	@Override
	public Iterable<CLInterpretableName> args() {
		return Arrays.asList(args);
	}

	@Override
	public int length(){
		return args.length;
	}

	@Override
	public CLBindingSequenceArray concat(CLBindingSequence inargs) {
		int bLen = inargs.length();
		CLInterpretableName[] b= new CLInterpretableName[bLen];
		int i = 0;
        for (final CLInterpretableName inarg : inargs.args())
        {
            b[i++] = inarg;
        }		
        CLInterpretableName[] c = CLArray.concatBindings(args, b);
		return new CLBindingSequenceArray(c);
	}

}
