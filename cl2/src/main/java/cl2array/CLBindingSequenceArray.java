/**
 * 
 */
package cl2array;

import java.util.Arrays;
import java.util.Collection;
import cl2a.CLInterpretableName;
import cl2a.CLBindingSequence;

/**
 * @author ralph
 *
 */
public class CLBindingSequenceArray extends CLBindingSequence {

	private final CLInterpretableName[] args;
	private final CLInterpretableName argHead;
	private final CLInterpretableName[] argTail;
	
	/**
	 * 
	 */
	public CLBindingSequenceArray(CLInterpretableName argHead, CLInterpretableName... argTail) {
		this.argHead = argHead;
		this.argTail = argTail;
		int aLen = argTail.length;
		CLInterpretableName[] c= new CLInterpretableName[aLen+1];
		c[0] = argHead;
		System.arraycopy(argTail, 0, c, 1, aLen);
		this.args = c;
	}

	@Override
	public Collection<CLInterpretableName> args() {
		return Arrays.asList(args);
	}

	@Override
	public int length(){
		return args.length;
	}

	@Override
	public CLBindingSequenceArray concat(CLBindingSequence inargs) {
		CLInterpretableName[] b = inargs.args().toArray(args);
        if (length() > 1) {
          CLInterpretableName[] c = CLArray.concatBindings(argTail, b);
    	  return new CLBindingSequenceArray(argHead, c);
    	}
    	else  {
    	  return new CLBindingSequenceArray(argHead, b);	  
        }
	}

}
