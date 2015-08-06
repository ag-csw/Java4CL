/**
 * 
 */
package cl2fj;

import java.util.Collection;

import cl2a.CLExpressionSequence;
import cl2a.CLExpression;
import fj.data.List;

/**
 * @author ralph
 *
 */
public class CLExpressionSequencefjList<A extends CLExpression> extends CLExpressionSequence {

	private final List<A> args;
	
	/**
	 * 
	 */
	public CLExpressionSequencefjList(List<A> args) {
		this.args = args;
	}

	@Override
	public Collection<A> args() {
		return args.toCollection();
	}

	@Override
	public int length(){
		return args.length();
	}

	@Override
	public CLExpressionSequencefjList<CLExpression> concat(CLExpressionSequence inargs) {
		List<CLExpression> b = List.nil();
        for (final CLExpression inarg : inargs.args())
        {
            b = b.cons(inarg);
        }		
        b = b.reverse();
		return new CLExpressionSequencefjList<CLExpression>(b);
	}

}
