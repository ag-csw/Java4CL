import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fj.F;
import fj.data.List;
import fj.data.State;
import functional.EqEither;
import functional.EqState;
import functional.ListPolyTree;

@RunWith(Suite.class)
@SuiteClasses({ EqStateTest.class //
})
public class AllEqStateTests {
	
	// EqState alone
	public static Integer int0 = 0;
	public static List<Integer> stack0 = List.nil();
	public static State<List<Integer>,Integer> statey0 = State.constant(2);
	public static EqState<List<Integer>,Integer> eqstatex0 = EqState.unit(int0);
	public static EqState<List<Integer>,Integer> eqstatey0 =EqState.eqState(statey0);
	public static F<Integer, String> f0 = s -> s.toString();
	public static F<Integer, EqState<List<Integer>,Integer>> g0 =
			s -> EqState.unit(s);
	public static F<EqState<List<Integer>,Integer>, 
	EqState<List<Integer>,EqState<List<Integer>,Integer>>> h0 =
	        s -> EqState.unit(s);
	
	

	// /PolyTrees

	public static List<EqEither<Integer, ListPolyTree<Integer>>> listEmpty = List
			.nil();

	public static EqEither<Integer, ListPolyTree<Integer>> either0 = EqEither
			.unitLeft(int0);
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listx0 = List
			.cons(EqEither.unitLeft(int0), listEmpty);
	public static ListPolyTree<Integer> treex0 = ListPolyTree.unit(int0);
	public static List<Integer> contentsy0 = List.cons(int0, List.list(int0));
	public static EqEither<Integer, ListPolyTree<Integer>> rightx0 = EqEither
			.unitRight(treex0);
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listy0 = List
			.cons(rightx0, listx0);
	public static ListPolyTree<Integer> treey0 = ListPolyTree.tree(listy0);
	public static F<Integer, ListPolyTree<Integer>> G0 = s -> treey0.insertLeaf(s);
	public static F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<Integer>>> H0 = s -> ListPolyTree
			.unit(treey0).insertLeaf(s).insertBranch(ListPolyTree.unit(treex0));

	public static Integer int1 = 1;
	public static EqEither<Integer, ListPolyTree<Integer>> either1 = EqEither
			.unitLeft(int1);
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listx1 = List
			.cons(EqEither.unitLeft(int1), listEmpty);
	public static ListPolyTree<Integer> treex1 = ListPolyTree.unit(int1);
	public static List<Integer> contentsy1 = contentsy0.append(List.cons(int1, contentsy0));
	public static EqEither<Integer, ListPolyTree<Integer>> righty1 = EqEither
			.unitRight(treey0);
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listy1 = List
			.cons(righty1, List.cons(either1, listy0));
	public static ListPolyTree<Integer> treey1 = ListPolyTree.tree(listy1);
	public static F<Integer, ListPolyTree<Integer>> G1 = s -> treey1.insertLeaf(s);
	public static F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<Integer>>> H1 = s -> ListPolyTree
			.unit(treey1).insertLeaf(s).insertBranch(ListPolyTree.unit(treex1));

	public static Integer int2 = 2;
	public static EqEither<Integer, ListPolyTree<Integer>> either2 = EqEither
			.unitLeft(int2);
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listx2 = List
			.cons(EqEither.unitLeft(int2), listEmpty);
	public static ListPolyTree<Integer> treex2 = ListPolyTree.unit(int2);
	public static List<Integer> contentsy2 = contentsy1.append(List.cons(int2, contentsy1));
	public static EqEither<Integer, ListPolyTree<Integer>> righty2 = EqEither
			.unitRight(treey1);
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listy2 = List
			.cons(righty2, List.cons(either2, listy1));
	public static ListPolyTree<Integer> treey2 = ListPolyTree.tree(listy2);
	public static F<Integer, ListPolyTree<Integer>> G2 = s -> treey2.insertLeaf(s);
	public static F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<Integer>>> H2 = s -> ListPolyTree
			.unit(treey2).insertLeaf(s).insertBranch(ListPolyTree.unit(treex2));

	static Integer n = (int) Math.pow(2, 12);

	public static List<Integer> contentsy5 = List.range(0, n);
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listy5 = contentsy5
			.map(s -> EqEither.unitLeft(s));
	public static ListPolyTree<Integer> treey5 = ListPolyTree.tree(listy5);

	public static List<Integer> contentsy6 = List.range(0, 2 * n);
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listy6 = contentsy6
			.map(s -> EqEither.unitLeft(s));
	public static ListPolyTree<Integer> treey6 = ListPolyTree.tree(listy6);

}
