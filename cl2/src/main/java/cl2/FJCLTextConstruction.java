/**
 * 
 */
package cl2;

import fj.F;
import fj.data.List;
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
	
	public static FJCLTextConstruction text(CLSentenceOrStatementOrText... args){
		return new FJCLTextConstruction(args);
	}

	public static FJCLTextConstruction text(List<CLSentenceOrStatementOrText> argsList){
		return new FJCLTextConstruction(argsList);
	}
	
	public static FJCLTextConstruction unit(CLSentenceOrStatementOrText x){
		return FJCLTextConstruction.text(x);
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

	// Must satisfy join(unit(unit(x))) = unit(x)
	public static FJCLTextConstruction join(FJCLTextConstruction x){
		List<CLSentenceOrStatementOrText> xargsList = x.argsList();
		List<CLSentenceOrStatementOrText> flatArgsList = xargsList.bind(s -> flatten1(s));
		return text(flatArgsList);
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

	public FJCLTextConstruction bind(F<CLSentenceOrStatementOrText, FJCLTextConstruction> f){
		List<FJCLTextConstruction> mapArgsList = argsList.map(f);
		List<List<CLSentenceOrStatementOrText>> argsListList = mapArgsList.map(argsList_());
		return text(List.join( argsListList ));
	}

	// Test: bind(s -> unit(f.f(s))) = map(f)
	// Test: must preserve composition: 
	// assertEquals( x.map(g).map(f) , map(f.compose(g)) );
	// Test: must preserve identity:   
	// assertEquals ( x.map(s -> s) , x )
	public FJCLTextConstruction map(F<CLSentenceOrStatementOrText, CLSentenceOrStatementOrText> f){
		return text(argsList.map(f));
	}

	// first-class version of map(f)
	public static F<F<CLSentenceOrStatementOrText, CLSentenceOrStatementOrText>, F<FJCLTextConstruction, FJCLTextConstruction>> map_() {
		return curry((f, as) -> as.map(f));
	}
	
}
