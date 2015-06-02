import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fj.F;
import fj.Ord;
import fj.data.Array;
import fj.data.Set;
import functional.EqEither;
import functional.EqSet;
import functional.EqSetPolyTree;

@RunWith(Suite.class)
@SuiteClasses({ 
	EqSetTest.class, //
	EqSetPolyTreeTest.class //
})

public class AllEqSetTests {

	//EqSet
	public static Integer intx0 = 0;
	public static Integer inty0 = 0;
	public static EqSet<Integer> kex0 = EqSet.unit(intx0);
	public static EqSet<Integer> key0 = EqSet.unit(inty0);
	public static Set<EqSet<Integer>> fjsety0 = Set.set(Ord.hashEqualsOrd(), key0);
	public static EqSet<EqSet<Integer>> eqsetx0 = EqSet.unit(kex0);
	public static EqSet<EqSet<Integer>> eqsety0 = EqSet.unit(key0);
	public static F<EqSet<Integer>, EqSet<EqSet<Integer>>> G0 = s -> EqSet.unit(s);
	public static F<EqSet<EqSet<Integer>>, EqSet<EqSet<EqSet<Integer>>>> H0 = s -> EqSet.unit(s);
	
	
	///EqSetPolyTrees
	public static Integer int4 = 0; 
	public static EqEither<Integer, EqSetPolyTree<Integer>> either4 = EqEither.unitLeft(int4); 
	public static EqSetPolyTree<Integer> treex4 = EqSetPolyTree.unit(int4);
	public static EqSet<Integer> contentsy4 = EqSet.eqSet(int4, 1);
	public static EqSet<EqEither<Integer, EqSetPolyTree<Integer>>> sety4 = EqSet.eqSet(EqEither.unitRight(treex4), EqEither.unitLeft(1));
	public static EqSetPolyTree<Integer> treey4 = EqSetPolyTree.tree(sety4);
	public static F<Integer, EqSetPolyTree<Integer>> G4 = s -> EqSetPolyTree.unit(int4).insertLeaf(s);

	public static F<EqSetPolyTree<Integer>, 
	                EqSetPolyTree<EqSetPolyTree<Integer>>> H4 = 
	                  s -> EqSetPolyTree.unit(treey4)
	                                      .insertLeaf(s).
	                                          insertBranch(EqSetPolyTree.unit(treex4));

	                  static Integer n = (int)Math.pow(2, 7);
	            	public static EqSet<Integer> contentsy5 = EqSet.eqSet(Set.iterableSet(Ord.intOrd, Array.range(0, n)));
	            	public static EqSet<EqEither<Integer, EqSetPolyTree<Integer>>> sety5 = contentsy5.map(s -> EqEither.unitLeft(s));
	            	public static EqSetPolyTree<Integer> treey5 = EqSetPolyTree.tree(sety5);

	            	public static EqSet<Integer> contentsy6 = EqSet.eqSet(Set.iterableSet(Ord.intOrd, Array.range(0, 2*n)));
	            	public static EqSet<EqEither<Integer, EqSetPolyTree<Integer>>> sety6 = contentsy6.map(s -> EqEither.unitLeft(s));
	            	public static EqSetPolyTree<Integer> treey6 = EqSetPolyTree.tree(sety6);


}
