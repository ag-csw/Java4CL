package functional;

import static fj.Function.curry;

import java.util.function.Function;

import fj.P2;
import fj.data.Java8;
import fj.data.State;
import fj.F;
import static fj.P.p;

public class EqState<S, A> {
	
	private EqState(final F<S, P2<S, A>> trans){
		this.trans = trans;
		this.state = State.unit(trans);
	}
	
	private EqState(final State<S, A> state){
		this.state = state;
		this.trans = s -> state.run(s);
	}
	
	private State<S, A> state;
	private F<S, P2<S, A>> trans;
	
	
	private State<S, A> state(){
		return state;
	}

	private static <T, B> State<T, B> state(final EqState<T, B> x){
		return x.state();
	}

	private static <T, B> F<EqState<T, B>, State<T, B>> state_() {
		return s -> state(s);
	}
	// static factory method
	public static <T, B> EqState<T, B> eqState(final F<T, P2<T, B>> f){
		return new EqState<T, B>(f);
	}
	
	public static <T, B> EqState<T, B> eqState(final State<T, B> t){
		return new EqState<T, B>(t);
	}
	
    public P2<S, A> run(S s ){
    	return trans.f(s);
    }

	//Monad methods
	// static unit method
	public static <T, B> EqState<T, B> unit(final B b){
		F<T, P2<T, B>> f = s -> p(s, b);
		EqState<T,B> result = new EqState<T,B>(f);
		return result;
	}
	
	
	//first-class version of unit
	public static <T, B> F< B , EqState<T, B>> unit_(){
		return f -> unit(f);
	}
	
	// join method
    public static <T, B> EqState<T, B> join(final EqState<T, EqState<T, B>> x){
    	State<T, B> y = x.state().flatMap(state_());
		return new EqState<T, B>(s -> y.run(s) );
    }
    
	// first-class version of join(x)
	public static <T, B> F<EqState<T, EqState<T, B>>, EqState<T, B>> join_() {
		return s -> join(s);
	}

	// map method
	public <B> EqState<S, B> map( final F<A, B> f) {
		return new EqState<S, B>(state.map(f) );
	}

	//static version of map
	public static <T, B, C> EqState<T, C> map(final F<B, C> f, final EqState<T, B> x) {
		return x.map(f);
	}

	// first-class version of map
	public static <T, B, C> F<F<B, C>, F<EqState<T, B>, EqState<T, C>>> map_() {
		return curry((f, as) -> as.map(f));
	}

	// bind method
	public <B> EqState<S, B> bind(final F<A, EqState<S, B>> f) {
		return join(map(f));
	}

	//static version of bind
	public static <T, B, C> EqState<T, C> bind(final F<B, EqState<T, C>> f, final EqState<T, B> x) {
		return x.bind(f);
	}

	// first-class version of bind
	public static <T, B, C> F<F<B, EqState<T, C>>, F<EqState<T, B>, EqState<T, C>>> bind_() {
		return curry((f, as) -> as.bind(f));
	}

	@Override
    public int hashCode() {
        Function<?, ?> dt = Java8.F_Function(this.trans);
        return dt.hashCode();
    }

	@Override
	public boolean equals(final Object o) {
        if (o == this)
            return true;

        if (!(o instanceof EqState))
            return false;
        EqState<?, ?> c = (EqState<?, ?>) o;
        Function<?, ?> ct = Java8.F_Function(c.trans);
        Function<?, ?> dt = Java8.F_Function(this.trans);
        try {
            return (ct == dt);
        } catch (ClassCastException unused)   {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
	}

}
