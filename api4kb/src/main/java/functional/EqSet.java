package functional;

import static fj.Function.curry;
import java.util.Iterator;

import fj.Ord;
import fj.data.Set;
import fj.F;

public class EqSet<A> implements Iterable<A> {
	
	private EqSet(Set<A> set){
		this.set = set;
	}
	private Set<A> set;
	//static factory method
	@SafeVarargs
	public static <B> EqSet<B> eqSet(B... as){
		return new EqSet<B>(Set.set(Ord.hashEqualsOrd(), as));
	}
	
	public static <B> EqSet<B> eqSet(Set<B> set){
		return new EqSet<B>(set);
	}
	
	public Set<A> set(){
		return set;
	}

	// assertEquals(EqSet.set(x), x.set());
	public static <B> Set<B> set( EqSet<B> x){
		return x.set();
	}

	// F<EqSet<B>, Set<B>> setf = EqSet.set_(); 
	// assertEquals(setf.f(x), x.set());
	public static <B> F<EqSet<B>, Set<B>> set_() {
		return s -> set(s);
	}
	
	public boolean member(A a) {
		return set.member(a);
	}
	
	public int size() {
		return set.size();
	}

	@Override
	public Iterator<A> iterator() {
		return set.iterator();
	}

	//Monad methods
	// static unit method
	public static <B> EqSet<B> unit(B b){
		return eqSet(b);
	}
	
	//first-class version of unit
    // assertEquals(EqSet.unit_().f(x), EqSet.unit(x));
	public static <B> F<B, EqSet<B>> unit_(){
		return b -> unit(b);
	}
	
	// join method
    //assertEquals( EqSet.join(EqSet.unit(EqSet.unit(x))), EqSet.unit(x) );
    public static <B> EqSet<B> join(EqSet<EqSet<B>> x){
		Ord<B> ordb = Ord.hashEqualsOrd();
		Ord<Set<B>> ordsetb = Ord.setOrd(ordb);
		F<EqSet<B>, Set<B>> setf = EqSet.set_(); 
    	return eqSet(Set.join(ordb, x.set().map(ordsetb, setf)));
    }
    
	// first-class version of join(x)
	// F<EqSet<EqSet<B>>, EqSet<B>> joinf = EqSet.join_(); 
    // assertEquals(joinf.f(x), EqSet.join(x));
	public static <B> F<EqSet<EqSet<B>>, EqSet<B>> join_() {
		return s -> join(s);
	}

	// map method
	// A x;
	// F<A, EqSet<A>> F;
    // assertEquals( EqSet.unit(F.f(x)) , EqSet.unit(x).map(F) );
	// Test: must preserve composition:
	// F<A, B> G;
	// F<B, C> H
    // assertEquals( w.map(F).map(G), w.map(Function.compose(G, F) )   );
	// Test: must preserve identity:   
	// EqSet<A> x;
    // assertEquals( x.map( s -> s ), x );	
	public <B> EqSet<B> map(
			F<A, B> f) {
		Ord<B> ordb = Ord.hashEqualsOrd();
		return eqSet(set.map(ordb, f));
	}

	//static version of map
	// Test: map(f, x) = x.map(f)
	public static <B, C> EqSet<C> map(F<B, C> f, EqSet<B> x) {
		return x.map(f);
	}

	// first-class version of map
	// Test: map_().apply(f).apply(x) = x.map(f)
	public static <B, C> F<F<B, C>, F<EqSet<B>, EqSet<C>>> map_() {
		return curry((f, as) -> as.map(f));
	}

	// bind method
	// A x;
	// F<A, EqSet<A>> F;
    // assertEquals(  EqSet.unit(x).bind(F) , F.f(x) );
	// EqSet<A> z;
    // assertEquals( z.bind(s -> EqSet.unit(F.f(s))) , z.map(F));
	public <B> EqSet<B> bind(F<A, EqSet<B>> f) {
		return join(map(f));
	}

	//static version of bind
	// Test: bind(f, x) = x.bind(f)
	public static <B, C> EqSet<C> bind(F<B, EqSet<C>> f, EqSet<B> x) {
		return x.bind(f);
	}

	// first-class version of bind
	// Test: bind_().apply(f).apply(x) = x.bind(f)
	public static <B, C> F<F<B, EqSet<C>>, F<EqSet<B>, EqSet<C>>> bind_() {
		return curry((f, as) -> as.bind(f));
	}

	@Override
    public int hashCode() {
        int h = 0;
        Iterator<A> i = iterator();
        while (i.hasNext()) {
            A obj = i.next();
            if (obj != null)
                h += obj.hashCode();
        }
        return h;
    }

	@Override
	public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof EqSet))
            return false;
        EqSet<?> c = (EqSet<?>) o;
        if (c.size() != size())
            return false;
        try {
            return containsAll(c);
        } catch (ClassCastException unused)   {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
	}
	public boolean containsAll(Iterable<?> c) {
		for (Object m : c){
			if(!contains(m)) return false;
		}
		return true;
	}
	public boolean contains(Object m) {
		for (A x : set){
			if( x.equals(m)) return true;
		}
		return false;
	}

}
