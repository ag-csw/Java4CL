/**
 * 
 */
package cl2array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cl2a.CLExpressionSequence;
import cl2a.CLExpression;

/**
 * @author ralph
 *
 */
public class CLExpressionSequenceArray extends CLExpressionSequence {

	private final CLExpression[] args;
	
	/**
	 * 
	 */
	public CLExpressionSequenceArray(CLExpression... args) {
		this.args = args;
	}

	@Override
	public Set<CLExpression> args() {
		return new HashSet<CLExpression>(Arrays.asList(args));
	}

	@Override
	public int length(){
		return args.length;
	}

	@Override
	public CLExpressionSequence concat(CLExpressionSequence inargs) {
		int bLen = inargs.length();
		CLExpression[] b= new CLExpression[bLen];
		int i = 0;
        for (final CLExpression inarg : inargs.args())
        {
            b[i++] = inarg;
        }		
        CLExpression[] c = CLArray.concatExpressions(args, b);
		return new CLExpressionSequenceArray(c);
	}

	@Override
	public CLExpressionSequenceArray copy() {
		return new CLExpressionSequenceArray(args);
	}
}
