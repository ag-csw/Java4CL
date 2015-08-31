/**
 * 
 */
package cl2fj;


import java.util.HashSet;

import cl2a.CLExpressionSet;
import cl2a.CLExpression;
import fj.data.Set;
import fj.F;
import fj.Ord;

/**
 * @author ralph
 *
 */
public class CLExpressionSequencefjList<A extends CLExpression> extends CLExpressionSet {

	private final Set<A> args;
	
	/**
	 * 
	 */
	public CLExpressionSequencefjList(Set<A> args) {
		this.args = args;
	}

	@Override
	public java.util.Set<A> args() {
		HashSet<A> result = new HashSet<A>();
		for (A e:args){
			result.add(e);
		}
		return result;
	}

	@Override
	public int length(){
		return args.size();
	}

	@Override
	public CLExpressionSequencefjList<CLExpression> concat(CLExpressionSet inargs) {
		Ord<CLExpression> ord = Ord.hashEqualsOrd();
		Set<CLExpression> result = fj.data.Set.empty(ord);
		for (CLExpression e:args()){
			result.insert(e);
		}
		for (CLExpression e:inargs.args()){
			result.insert(e);
		}
		return new CLExpressionSequencefjList<CLExpression>(result);
	}

	@Override
	public CLExpressionSequencefjList<A> copy() {
		Ord<A> ordA = Ord.hashEqualsOrd();
		@SuppressWarnings("unchecked")
		F<A, A> f = s -> (A) s.copy();
		return new CLExpressionSequencefjList<A>(args.map(ordA, f));
	}
}
