package api4kbfjset;

import static fj.Function.curry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fj.F;
import fj.Ord;
import fj.data.Set;
import api4kbj.KRRLanguage;
import api4kbj.StructuredKnowledgeExpression;

public class FJSetStructuredKnowledgeExpression extends FJSetKnowledgeExpression implements StructuredKnowledgeExpression  {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	private FJSetStructuredKnowledgeExpression(final Set<KRRLanguage> languages,
			final Set<FJSetKnowledgeExpression> components) {
		super(languages);
		this.components = components;
	}

	private FJSetStructuredKnowledgeExpression(
			final Set<FJSetKnowledgeExpression> components) {
		this(components.bind(langOrder, s -> languages(s) ), components);
	}

    private static Ord<KRRLanguage> langOrder = Ord.hashEqualsOrd();

	private final Set<FJSetKnowledgeExpression> components;
	
	//factory methods
	public static FJSetStructuredKnowledgeExpression ke(final Set<FJSetKnowledgeExpression> components){
		return new FJSetStructuredKnowledgeExpression(components);
	}

	public static FJSetStructuredKnowledgeExpression ke(final Set<KRRLanguage> languages,
			Set<FJSetKnowledgeExpression> components){
		return new FJSetStructuredKnowledgeExpression(languages, components);
	}

	@Override
	public Set<FJSetKnowledgeExpression> components() {
		return components;
	}

	public static Set<FJSetKnowledgeExpression> components(final FJSetStructuredKnowledgeExpression s) {
		return s.components();
	}

	// first-class version of components(x)
	public static F<FJSetStructuredKnowledgeExpression, Set<FJSetKnowledgeExpression>> components_() {
		return s -> components(s);
	}

	@Override
	public int numComponents() {
		return components.size();
	}

	//Monad methods
	public static FJSetStructuredKnowledgeExpression unit(FJSetKnowledgeExpression component){
		return ke(Set.set(Ord.hashEqualsOrd(), component));
	}


	// Must satisfy join(unit(unit(x))) = unit(x)
	public static FJSetStructuredKnowledgeExpression join(final FJSetStructuredKnowledgeExpression x){
		return ke(x.components().
				      bind(Ord.hashEqualsOrd(), s -> flatten1(s)));		
	}
	
	// first-class version of join(x)
	public static F<FJSetStructuredKnowledgeExpression, FJSetStructuredKnowledgeExpression> join_() {
		return s -> join(s);
	}
	
	private static Set<FJSetKnowledgeExpression> flatten1(final FJSetKnowledgeExpression x){
		if(x instanceof FJSetStructuredKnowledgeExpression){
			return ((FJSetStructuredKnowledgeExpression) x).components();
		}
		return Set.set(Ord.hashEqualsOrd(), x);
	}

	public FJSetStructuredKnowledgeExpression bind(final F<FJSetKnowledgeExpression, FJSetStructuredKnowledgeExpression> f){
		return ke(components.
				    map(Ord.hashEqualsOrd(), f).
				        bind(Ord.hashEqualsOrd(), components_()));
	}
	
	// Test: bind(s -> unit(f.f(s))) = map(f)
	// Test: must preserve composition: 
	// assertEquals( x.map(g).map(f) , map(f.compose(g)) );
	// Test: must preserve identity:   
	// assertEquals ( x.map(s -> s) , x )
	public FJSetStructuredKnowledgeExpression map(final F<FJSetKnowledgeExpression, FJSetKnowledgeExpression> f){
		return ke(components().map(Ord.hashEqualsOrd(), f));
	}

	// first-class version of map(f)
	public static F<F<FJSetKnowledgeExpression, FJSetKnowledgeExpression>, F<FJSetStructuredKnowledgeExpression, FJSetStructuredKnowledgeExpression>> map_() {
		return curry((f, as) -> as.map(f));
	}

	@Override
	public FJSetStructuredKnowledgeExpression copy() {
		return new FJSetStructuredKnowledgeExpression(languages(), components);
	}

}
