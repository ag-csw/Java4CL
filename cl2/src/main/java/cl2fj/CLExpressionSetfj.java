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
public class CLExpressionSetfj<A extends CLExpression> extends CLExpressionSet {

	private final Set<A> args;
	
	public CLExpressionSetfj(Set<A> args) {
	   if (args.member(null))
			throw new NullPointerException("Arguments to CLExpressionSet constructor should not be null");
		this.args = args;
	}
	
	//public static <B extends CLExpression> CLExpressionSetfj<B> createCLExpressionSetfj(final Ord<B> ordB, final B... args){
	//	return new CLExpressionSetfj<B>(Set.set(ordB, args));
	//}
		
	public static <B extends CLExpression> CLExpressionSetfj<B> createCLExpressionSetfj(java.util.Set<B> inargs){
		Ord<B> ordB = Ord.hashEqualsOrd();
		Set<B> result = Set.empty(ordB);
		for (B e:inargs){
			result.insert(e);
		}
		return new CLExpressionSetfj<B>(result);
		
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
	public CLExpressionSetfj<CLExpression> concat(CLExpressionSet inargs) {
		@SuppressWarnings("unchecked")
		Set<CLExpression> result = (Set<CLExpression>) args;
		for (CLExpression e:inargs.args()){
			result.insert(e);
		}
		return new CLExpressionSetfj<CLExpression>(result);
	}

	@Override
	public CLExpressionSetfj<A> copy() {
		@SuppressWarnings("unchecked")
		F<A, A> f = s -> (A) s.copy();
		return new CLExpressionSetfj<A>(args.map(args.ord(), f));
	}
}
