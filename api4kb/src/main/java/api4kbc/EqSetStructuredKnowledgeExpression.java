package api4kbc;

import static fj.Function.curry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fj.F;
import fj.Function;
import fj.Ord;
import fj.data.Set;
import functional.EqSet;
import api4kbj.KRRLanguage;
import api4kbj.StructuredKnowledgeExpression;

public class EqSetStructuredKnowledgeExpression extends EqSetKnowledgeExpression implements StructuredKnowledgeExpression  {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	private EqSetStructuredKnowledgeExpression(EqSet<KRRLanguage> languages,
			EqSet<EqSetKnowledgeExpression> components) {
		super(languages);
		this.components = components;
	}

	private EqSetStructuredKnowledgeExpression(
			EqSet<EqSetKnowledgeExpression> components) {
		this(components.bind( languages_() ), components);
	}

	private final EqSet<EqSetKnowledgeExpression> components;
	
	//factory methods
	public static EqSetStructuredKnowledgeExpression ke(EqSet<EqSetKnowledgeExpression> components){
		return new EqSetStructuredKnowledgeExpression(components);
	}

	public static EqSetStructuredKnowledgeExpression ke(EqSet<KRRLanguage> languages,
			EqSet<EqSetKnowledgeExpression> components){
		return new EqSetStructuredKnowledgeExpression(languages, components);
	}

	@Override
	public EqSet<EqSetKnowledgeExpression> components() {
		return components;
	}

	public static EqSet<EqSetKnowledgeExpression> components(EqSetStructuredKnowledgeExpression s) {
		return s.components();
	}

	// first-class version of components(x)
	public static F<EqSetStructuredKnowledgeExpression, EqSet<EqSetKnowledgeExpression>> components_() {
		return s -> components(s);
	}

	@Override
	public int numComponents() {
		return components.size();
	}

	//Monad methods
	public static EqSetStructuredKnowledgeExpression unit(EqSetKnowledgeExpression component){
		return ke(EqSet.eqSet(component));
	}


	// Must satisfy join(unit(unit(x))) = unit(x)
	public static EqSetStructuredKnowledgeExpression join(EqSetStructuredKnowledgeExpression x){
		return ke(x.components().
				      bind( s -> flatten1(s)));		
	}
	
	// first-class version of join(x)
	public static F<EqSetStructuredKnowledgeExpression, EqSetStructuredKnowledgeExpression> join_() {
		return s -> join(s);
	}
	
	private static EqSet<EqSetKnowledgeExpression> flatten1(EqSetKnowledgeExpression x){
		if(x instanceof EqSetStructuredKnowledgeExpression){
			return ((EqSetStructuredKnowledgeExpression) x).components();
		}
		return EqSet.eqSet(x);
	}

	public EqSetStructuredKnowledgeExpression bind(F<EqSetKnowledgeExpression, EqSetStructuredKnowledgeExpression> f){
		return ke(components.bind(Function.compose(components_(), f)));
	}
	
	// Test: bind(s -> unit(f.f(s))) = map(f)
	// Test: must preserve composition: 
	// assertEquals( x.map(g).map(f) , map(f.compose(g)) );
	// Test: must preserve identity:   
	// assertEquals ( x.map(s -> s) , x )
	public EqSetStructuredKnowledgeExpression map(F<EqSetKnowledgeExpression, EqSetKnowledgeExpression> f){
		return ke(components().map(f));
	}

	// first-class version of map(f)
	public static F<F<EqSetKnowledgeExpression, EqSetKnowledgeExpression>, F<EqSetStructuredKnowledgeExpression, EqSetStructuredKnowledgeExpression>> map_() {
		return curry((f, as) -> as.map(f));
	}

}
