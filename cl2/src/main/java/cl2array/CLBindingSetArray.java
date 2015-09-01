/**
 * 
 */
package cl2array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cl2a.CLComment;
import cl2a.CLCommentSet;
import cl2a.CLExpression;
import cl2a.CLInterpretableName;
import cl2a.CLBindingSet;

public class CLBindingSetArray extends CLBindingSet {

	private final CLInterpretableName[] bindings;
	
	/**
	 * 
	 */
	public CLBindingSetArray(CLInterpretableName... bindings) {
		super(bindings);
		this.bindings = bindings;
	}

	public <T extends CLInterpretableName> CLBindingSetArray(Set<T> bindings) {
		this(bindings.toArray(new CLInterpretableName[bindings.size()]));
	}

	@Override
	public Set<? extends CLInterpretableName> bindings() {
		return new HashSet<CLInterpretableName>(Arrays.asList(bindings));
	}

	@Override
	public int length(){
		return bindings.length;
	}

	@Override
	public CLBindingSetArray concat(CLBindingSet inbindings) {
		int bLen = inbindings.length();
		CLInterpretableName[] b= new CLInterpretableName[bLen];
		int i = 0;
        for (final CLInterpretableName incomment : inbindings.bindings())
        {
            b[i++] = incomment;
        }		
        CLInterpretableName[] c = CLArray.concatBindings(bindings, b);
		return new CLBindingSetArray(c);
	}

	@Override
	public CLBindingSetArray copy() {
		return new CLBindingSetArray( CLArray.copyBindings(bindings));
	}
}
