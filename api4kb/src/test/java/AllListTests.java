import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fj.F;
import fj.data.List;
import functional.EqEither;
import functional.ListPolyTree;

@RunWith(Suite.class)
@SuiteClasses({ 
	ListPolyTreeTest.class //
})

public class AllListTests {

	
	
	///PolyTrees

	
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listEmpty = List.nil();

	public static Integer int0 = 0; 
	public static EqEither<Integer, ListPolyTree<Integer>> either0 = EqEither.unitLeft(int0); 
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listx0 = List.cons(EqEither.unitLeft(int0), listEmpty); 
	public static ListPolyTree<Integer> treex0 = ListPolyTree.unit(int0);
	public static List<Integer> contentsy0 = List.cons(int0, List.list(int0));
	public static EqEither<Integer, ListPolyTree<Integer>> rightx0 = EqEither.unitRight(treex0);
	public static List<EqEither<Integer, ListPolyTree<Integer>>> listy0 = List.cons(rightx0, listx0);
	public static ListPolyTree<Integer> treey0 = ListPolyTree.tree(listy0);
	public static F<Integer, ListPolyTree<Integer>> G0 = s -> ListPolyTree.unit(int0).insertLeaf(s);
	public static F<ListPolyTree<Integer>, 
    ListPolyTree<ListPolyTree<Integer>>> H0 = 
      s -> ListPolyTree.unit(treey0)
                          .insertLeaf(s).
                              insertBranch(ListPolyTree.unit(treex0));

	
	
  	public static Integer int1 = 1; 
  	public static EqEither<Integer, ListPolyTree<Integer>> either1 = EqEither.unitLeft(int1); 
  	public static List<EqEither<Integer, ListPolyTree<Integer>>> listx1 = List.cons(EqEither.unitLeft(int1), listEmpty); 
  	public static ListPolyTree<Integer> treex1 = ListPolyTree.unit(int1);
  	public static List<Integer> contentsy1 = List.cons(int1, List.list(int0));
  	public static EqEither<Integer, ListPolyTree<Integer>> rightx1 = EqEither.unitRight(treex1);
  	public static List<EqEither<Integer, ListPolyTree<Integer>>> listy1 = List.cons(rightx1, listx0);
  	public static ListPolyTree<Integer> treey1 = ListPolyTree.tree(listy1);
  	public static F<Integer, ListPolyTree<Integer>> G1 = s -> ListPolyTree.unit(int1).insertLeaf(s);
  	public static F<ListPolyTree<Integer>, 
      ListPolyTree<ListPolyTree<Integer>>> H1 = 
        s -> ListPolyTree.unit(treey1)
                            .insertLeaf(s).
                                insertBranch(ListPolyTree.unit(treex1));

	                  static Integer n = (int)Math.pow(2, 12);
	                  
	                  
	            	public static List<Integer> contentsy5 = List.range(0, n);
	            	public static List<EqEither<Integer, ListPolyTree<Integer>>> listy5 = contentsy5.map(s -> EqEither.unitLeft(s));
	            	public static ListPolyTree<Integer> treey5 = ListPolyTree.tree(listy5);

	            	public static List<Integer> contentsy6 = List.range(0, 2*n);
	            	public static List<EqEither<Integer, ListPolyTree<Integer>>> listy6 = contentsy6.map(s -> EqEither.unitLeft(s));
	            	public static ListPolyTree<Integer> treey6 = ListPolyTree.tree(listy6);


}

