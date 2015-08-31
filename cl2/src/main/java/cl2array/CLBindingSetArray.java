/**
 * 
 */
package cl2array;

import java.util.Arrays;
import java.util.Collection;

import cl2a.CLComment;
import cl2a.CLCommentSet;
import cl2a.CLInterpretableName;
import cl2a.CLBindingSet;

/**
 * @author ralph
 *
 */
public class CLBindingSetArray extends CLBindingSet {

	private final CLInterpretableName[] names;
	
	/**
	 * 
	 */
	public CLBindingSetArray(CLInterpretableName... names) {
		super(names);
		this.names = names;
	}

	@Override
	public Set<? extends CLInterpretableName> args() {
		return Arrays.asList(names);
	}

	@Override
	public int length(){
		return names.length;
	}

	@Override
	public CLBindingSetArray concat(CLBindingSet innames) {
		int bLen = innames.length();
		CLInterpretableName[] b= new CLInterpretableName[bLen];
		int i = 0;
        for (final CLInterpretableName incomment : innames.args())
        {
            b[i++] = incomment;
        }		
        CLInterpretableName[] c = CLArray.concatBindings(names, b);
		return new CLBindingSetArray(c);
	}

	@Override
	public CLBindingSetArray copy() {
		return new CLBindingSetArray( CLArray.copyBindings(names));
	}
}
