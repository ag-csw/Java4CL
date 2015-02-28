/**
 * 
 */
package cl2;

import fj.F;
import fj.data.List;
import functional.EqSet;
import static fj.Function.curry;

/**
 * @author tara
 *
 */
public class FJCLTextConstruction extends CLSentenceOrStatementOrText {
	
	private FJCLTextConstruction(CLSentenceOrStatementOrText... args) {
		this.argsList = List.list(args);
	}

	private FJCLTextConstruction(List<CLSentenceOrStatementOrText> argsList) {
		this.argsList = argsList;
	}

	private List<CLSentenceOrStatementOrText> argsList;
	
	//factory methods
	public static FJCLTextConstruction text(CLSentenceOrStatementOrText... args){
		return new FJCLTextConstruction(args);
	}

	public static FJCLTextConstruction text(List<CLSentenceOrStatementOrText> argsList){
		return new FJCLTextConstruction(argsList);
	}
	
	public List<CLSentenceOrStatementOrText> argsList() {
		return argsList;
	}

	public static List<CLSentenceOrStatementOrText> argsList(FJCLTextConstruction x) {
		return x.argsList;
	}

	// first-class version of argsList(x)
	public static F<FJCLTextConstruction, List<CLSentenceOrStatementOrText>> argsList_() {
		return s -> argsList(s);
	}

	//Monad methods
	// unit method
	public static FJCLTextConstruction unit(CLSentenceOrStatementOrText x){
		return text(x);
	}

	//first-class version of unit
	// assertEquals(unit_().apply(x), unit(x))
	public static F<CLSentenceOrStatementOrText, FJCLTextConstruction> unit_(){
		return b -> unit(b);
	}

	
	// join method
	// Must satisfy join(unit(unit(x))) = unit(x)
	public static FJCLTextConstruction join(FJCLTextConstruction x){
		return text(x.argsList().
				         bind(s -> flatten1(s)));
	}

	// first-class version of join(x)
	public static F<FJCLTextConstruction, FJCLTextConstruction> join_() {
		return s -> join(s);
	}
	
	private static List<CLSentenceOrStatementOrText> flatten1(CLSentenceOrStatementOrText x){
		if(x instanceof FJCLTextConstruction){
			return ((FJCLTextConstruction) x).argsList();
		}
		return List.list(x);
	}

	// bind method
	public FJCLTextConstruction bind(F<CLSentenceOrStatementOrText, FJCLTextConstruction> f){
		return text(argsList.
			          map(f).
			            bind(argsList_()));

	}

	//static version of bind
	// Test: bind(f, x) = x.bind(f)
	public static FJCLTextConstruction bind(F<CLSentenceOrStatementOrText, FJCLTextConstruction> f, FJCLTextConstruction x) {
		return x.bind(f);
	}

	// first-class version of bind
	// Test: bind_().apply(f).apply(x) = x.bind(f)
	public static F<
	                  F<CLSentenceOrStatementOrText, FJCLTextConstruction>, 
	                  F<FJCLTextConstruction, FJCLTextConstruction>
	               > bind_() {
		return curry((f, as) -> as.bind(f));
	}

	// map method
	// Test: bind(s -> unit(f.f(s))) = map(f)
	// Test: must preserve composition: 
	// assertEquals( x.map(g).map(f) , map(f.compose(g)) );
	// Test: must preserve identity:   
	// assertEquals ( x.map(s -> s) , x )
	public FJCLTextConstruction map(F<CLSentenceOrStatementOrText, CLSentenceOrStatementOrText> f){
		return text(argsList.map(f));
	}

	//static version of map
	// Test: map(f, x) = x.map(f)
	public static FJCLTextConstruction map(F<CLSentenceOrStatementOrText, CLSentenceOrStatementOrText> f, FJCLTextConstruction x) {
		return x.map(f);
	}

	// first-class version of map
	// Test: map_().apply(f).apply(x) = x.map(f)
	public static F<
	                  F<CLSentenceOrStatementOrText, CLSentenceOrStatementOrText>, 
	                  F<FJCLTextConstruction, FJCLTextConstruction>
	               > map_() {
		return curry((f, as) -> as.map(f));
	}
	
}
