import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fj.F;
import fj.Function;
import functional.EqEither;
import fj.data.List;
import functional.ListPolyTree;

@RunWith(Parameterized.class)
@Ignore
public class ListPolyTreeTest {

	public ListPolyTreeTest(
			Integer ix,
			List<Integer> contentsy,
			List<EqEither<Integer, ListPolyTree<Integer>>> sety,
			ListPolyTree<Integer> treex,
			ListPolyTree<Integer> treey,
			F<Integer, ListPolyTree<Integer>> g,
			F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<Integer>>> h) {
		super();
		this.ix = ix;
		this.contentsy = contentsy;
		this.sety = sety;
		this.treex = treex;
		this.treey = treey;
		G = g;
		H = h;
		K = Function.compose(H, G);
		x = ListPolyTree.unit(treex);
		setz = List.nil();
		treez = ListPolyTree.tree(setz);
		//in = null;
		//setn = List.list(in);
		//treen = ListPolyTree.tree(setn);
		//n = ListPolyTree.unit(treen);
	}

	//public EqEither<Integer, ListPolyTree<Integer>> in;
	public Integer ix;
	public List<Integer> contentsy;
	public List<EqEither<Integer, ListPolyTree<Integer>>> sety;
	public ListPolyTree<Integer> treex;
	public ListPolyTree<Integer> treey;

	public F<Integer, ListPolyTree<Integer>> G;
	public F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<Integer>>> H;
	
	public List<EqEither<Integer, ListPolyTree<Integer>>> setz;
	//public List<EqEither<Integer, ListPolyTree<Integer>>> setn;
	public ListPolyTree<Integer> treez;
	//public ListPolyTree<Integer> treen;
    public F<Integer, ListPolyTree<ListPolyTree<Integer>>> K;
	public ListPolyTree<ListPolyTree<Integer>> x;
	//public ListPolyTree<ListPolyTree<Integer>> n;

	@Test
	public final void equalsShouldWork() {
		assertEquals(EqEither.unitLeft(ix), EqEither.unitLeft(ix));
		assertEquals(List.list(EqEither.unitLeft(ix)), List.list(EqEither.unitLeft(ix)));
		assertEquals(treex, treex);
		assertEquals(ListPolyTree.tree(List.list(EqEither.unitLeft(ix))), ListPolyTree.tree(List.list(EqEither.unitLeft(ix))));
		//assertEquals(treex, ListPolyTree.unit(ix));
	}

	@Test
	public void singletonTreeShouldBeAsConstructed() {
		assertTrue(treex.contains(ix));
		assertTrue(treex.size() == 1);
	}

	@Test
	public final void treeShouldBeEqualToOtherListBuiltFromStaticFactorySetMethod() {
		assertEquals(treey, ListPolyTree.tree(sety));
	}

	@Test
	public void treeShouldBeAsConstructed() {
		assertTrue(treey.containsAll(contentsy));
		assertTrue(treey.size() == contentsy.length());
	}

	@Test
	public final void singletonShouldContainExpression() {
		assertTrue(treex.contains(ix));
	}

	@Test
	public final void treeWithNullShouldBeMappable() {
		//assertEquals(treen.map(s -> s), treen);
		//assertEquals(treen.bind(ListPolyTree::unit), treen);
	}

	@Test
	public final void differentListsShouldNotBeEqual() {
		assertFalse(treex.equals(treez)); //different size
		assertFalse(treex.equals(x)); //different type parameter
		assertFalse(treex.equals(ix)); //different class
		assertFalse(treex.equals(G)); //different class
		//assertFalse(treez.equals(treen));
		//assertFalse(treen.equals(treex));
		assertFalse(treex.equals(null));
	}

	@Test
	public final void treeShouldNotContainNonMember() {
		assertFalse(treex.contains(ix + 1));
	}
	
	@Test
	public final void treeShouldHaveSizeOfSet() {
		//assertEquals(treey.size(), sety.length());
		assertEquals(treex.size(), 1);
		assertEquals(treez.size(), 0);
		//assertEquals(treen.size(), 1);
		assertEquals(x.size(), 1);
	}

	@Test
	public final void firstClassUnitShouldEqualStaticUnit() {
		assertEquals(ListPolyTree.unit_().f(ix), ListPolyTree.unit(ix));
		assertEquals(ListPolyTree.unit_().f(treex), ListPolyTree.unit(treex));
		assertEquals(ListPolyTree.unit_().f(treey), ListPolyTree.unit(treey));
		assertEquals(ListPolyTree.unit_().f(x), ListPolyTree.unit(x));
	}

	@Test
	public final void firstClassJoinShouldEqualStaticJoin() {
		F<ListPolyTree<ListPolyTree<Integer>>, ListPolyTree<Integer>> joinf = ListPolyTree
				.join_();
		assertEquals(joinf.f(x), ListPolyTree.join(x));
	}

	
	@Test
	public final void xMapFShouldEqualStaticMapFX() {
		assertEquals(treex.map(G), ListPolyTree.map(G, treex));
		assertEquals(treex.map(K), ListPolyTree.map(K, treex));
		assertEquals(treey.map(G), ListPolyTree.map(G, treey));
		assertEquals(treey.map(K), ListPolyTree.map(K, treey));
		assertEquals(treez.map(G), ListPolyTree.map(G, treez));
		assertEquals(treez.map(K), ListPolyTree.map(K, treez));
		assertEquals(x.map(H), ListPolyTree.map(H, x));
	}

	@Test
	public final void xMapFShouldEqualFirstClassMapFX() {
		F<F<Integer,ListPolyTree<Integer>>, F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<Integer>>>> map1 = ListPolyTree.map_();
		F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<Integer>>> mapG = map1.f(G);
		assertEquals(treex.map(G), mapG.f(treex));
		assertEquals(treey.map(G), mapG.f(treey));
		assertEquals(treez.map(G), mapG.f(treez));
	}

	@Test
	public final void xBindFShouldEqualStaticBindFX() {
		assertEquals(treex.bind(G), ListPolyTree.bind(G, treex));
		assertEquals(treex.bind(K), ListPolyTree.bind(K, treex));
		assertEquals(treex.bind(ListPolyTree::unit), ListPolyTree.bind(ListPolyTree::unit, treex));
		assertEquals(treey.bind(G), ListPolyTree.bind(G, treey));
		assertEquals(treey.bind(K), ListPolyTree.bind(K, treey));
		assertEquals(treey.bind(ListPolyTree::unit), ListPolyTree.bind(ListPolyTree::unit, treey));
		assertEquals(treez.bind(G), ListPolyTree.bind(G, treez));
		assertEquals(treez.bind(K), ListPolyTree.bind(K, treez));
		assertEquals(treez.bind(ListPolyTree::unit), ListPolyTree.bind(ListPolyTree::unit, treez));
		assertEquals(x.bind(H), ListPolyTree.bind(H, x));
		assertEquals(x.bind(ListPolyTree::unit), ListPolyTree.bind(ListPolyTree::unit, x));
	}

	@Test
	public final void xBindFShouldEqualFirstClassBindFX() {
		F<F<Integer,ListPolyTree<Integer>>, F<ListPolyTree<Integer>, ListPolyTree<Integer>>> bind1 = ListPolyTree.bind_();
		F<ListPolyTree<Integer>, ListPolyTree<Integer>> bindG = bind1.f(G);
		assertEquals(treex.bind(G), bindG.f(treex));
		assertEquals(treey.bind(G), bindG.f(treey));
		assertEquals(treez.bind(G), bindG.f(treez));
	}


	// Functor Law 
	// Functor Identity
	@Test
	public final void mapShouldPreserveIdentityF() {
		assertEquals(treex.map(s -> s), treex);
		assertEquals(treey.map(s -> s), treey);
		assertEquals(x.map(s -> s), x);
	}

	// Functor Associativity
	@Test
	public final void mapShouldPreserveComposition() {
		assertEquals(treex.map(G).map(H), treex.map(K));
		assertEquals(treey.map(G).map(H), treey.map(K));
	}

	// Natural Transformation (Unit)
	@Test
	public final void unitFShouldEqualUnitMapF() {
		assertEquals(ListPolyTree.unit(G.f(ix)), ListPolyTree.unit(ix).map(G));
		assertEquals(ListPolyTree.unit(K.f(ix)), ListPolyTree.unit(ix).map(K));
		assertEquals(ListPolyTree.unit(H.f(treex)), ListPolyTree.unit(treex).map(H));
		assertEquals(ListPolyTree.unit(H.f(treey)), ListPolyTree.unit(treey).map(H));
		assertEquals(ListPolyTree.unit(ix), ListPolyTree.unit(ix).map(s -> s));
	}


	// Monad Right Identity Law
	
	//join . fmap join     ≡ join . join
	@Test
	public final void joinMapJoinShouldEqualJoinJoin() {
		ListPolyTree<ListPolyTree<ListPolyTree<Integer>>> ux = ListPolyTree.unit(x);
		ListPolyTree<ListPolyTree<ListPolyTree<Integer>>> uy = ListPolyTree.unit(ListPolyTree.unit(treey));
		ListPolyTree<ListPolyTree<ListPolyTree<Integer>>> uz = ListPolyTree.unit(ListPolyTree.unit(treez));
		ListPolyTree<ListPolyTree<ListPolyTree<ListPolyTree<Integer>>>> uxx = ListPolyTree.unit(ListPolyTree.unit(x));
		assertEquals( ListPolyTree.join(ux.map(ListPolyTree.join_())),
			      ListPolyTree.join(ListPolyTree.join(ux)));
		assertEquals( ListPolyTree.join(uy.map(ListPolyTree.join_())),
			      ListPolyTree.join(ListPolyTree.join(uy)));
		assertEquals( ListPolyTree.join(uz.map(ListPolyTree.join_())),
			      ListPolyTree.join(ListPolyTree.join(uz)));
		assertEquals( ListPolyTree.join(uxx.map(ListPolyTree.join_())),
			      ListPolyTree.join(ListPolyTree.join(uxx)));
		
		ListPolyTree<ListPolyTree<ListPolyTree<Integer>>> wx = ux.insertLeaf(ListPolyTree.unit(treey));
		ListPolyTree<ListPolyTree<ListPolyTree<Integer>>> vy = ListPolyTree.unit(ListPolyTree.unit(treey).insertLeaf(treex).insertBranch(x));
		ListPolyTree<ListPolyTree<ListPolyTree<Integer>>> wy = vy.insertBranch(wx);
		assertEquals( wx.map(ListPolyTree.join_()),
			      ListPolyTree.join(wx));
		assertEquals( ListPolyTree.join(wy.map(ListPolyTree.join_())),
				ListPolyTree.join(ListPolyTree.join(wy)));
	}
	
	// Monad Left Identity Law
	// join . fmap return   ≡ id 
	@Test
	public final void joinMapUnitShouldBeIdentity() {
		assertEquals(ListPolyTree.join(treex.map(ListPolyTree.unit_())),
		               treex);
		assertEquals(ListPolyTree.join(treey.map(ListPolyTree.unit_())),
                treey);
		assertEquals(ListPolyTree.join(treez.map(ListPolyTree.unit_())),
                treez);
		assertEquals(ListPolyTree.join(x.map(ListPolyTree.unit_())),
	                   x);
	}
	// join . return = id
	@Test
	public final void joinUnitShouldBeIdentity() {
		assertEquals(ListPolyTree.join(ListPolyTree.unit(treex)), treex);
		assertEquals(ListPolyTree.join(ListPolyTree.unit(treey)), treey);
		assertEquals(ListPolyTree.join(ListPolyTree.unit(treez)), treez);
		assertEquals(ListPolyTree.join(ListPolyTree.unit(x)), x);
		
	}
	
	// redundant, given bind = join(map)
	@Test
	public final void bindUnitShouldEqualIdentity() {
		assertEquals(treex.bind(ListPolyTree::unit), treex);
		assertEquals(treey.bind(ListPolyTree::unit), treey);
		assertEquals(treez.bind(ListPolyTree::unit), treez);
		assertEquals(x.bind(ListPolyTree::unit), x);		
	}
	
	
	
	//join . fmap (fmap f) ≡ fmap f . join
	@Test
	public final void joinMapMapFShouldBeMapFJoin() {
		F<    F<Integer, ListPolyTree<Integer>>,
		      F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<Integer>>>  > map1 = ListPolyTree.map_();
		F<    F<Integer, ListPolyTree<ListPolyTree<Integer>>>,
	      F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<ListPolyTree<Integer>>>>  > map2 = ListPolyTree.map_();
		F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<Integer>>> mapG = map1.f(G);
		F<ListPolyTree<Integer>, ListPolyTree<ListPolyTree<ListPolyTree<Integer>>>> mapK = map2.f(K);
		ListPolyTree<ListPolyTree<Integer>> y = ListPolyTree.unit(treey);
		ListPolyTree<ListPolyTree<Integer>> z = ListPolyTree.unit(treez);
		assertEquals( ListPolyTree.join(x.map(mapG)),
				  ListPolyTree.join(x).map(G));
		assertEquals(ListPolyTree.join(y.map(mapG)),
				  ListPolyTree.join(y).map(G));
		assertEquals(ListPolyTree.join(z.map(mapG)),
				  ListPolyTree.join(z).map(G));
		assertEquals( ListPolyTree.join(x.map(mapK)),
				  ListPolyTree.join(x).map(K));
		assertEquals(ListPolyTree.join(y.map(mapK)),
				  ListPolyTree.join(y).map(K));
		assertEquals(ListPolyTree.join(z.map(mapK)),
				  ListPolyTree.join(z).map(K));
		
		assertEquals( x.bind(mapG),
				  ListPolyTree.join(x).map(G));
		assertEquals( y.bind(mapG),
				  ListPolyTree.join(y).map(G));

	}

	//redundant, given bind = join(map) and join(unit) = id
	@Test
	public final void unitBindFShouldEqualF() {
		assertEquals(ListPolyTree.unit(ix).bind(ListPolyTree::unit), ListPolyTree.unit(ix));
		assertEquals(ListPolyTree.unit(ix).bind(G), G.f(ix));
		assertEquals(ListPolyTree.unit(ix).bind(K), K.f(ix));
		assertEquals(ListPolyTree.unit(treex).bind(ListPolyTree::unit), ListPolyTree.unit(treex));
		assertEquals(ListPolyTree.unit(treex).bind(H), H.f(treex));
		assertEquals(ListPolyTree.unit(treey).bind(ListPolyTree::unit), ListPolyTree.unit(treey));
		assertEquals(ListPolyTree.unit(treey).bind(H), H.f(treey));
		assertEquals(ListPolyTree.unit(x).bind(ListPolyTree::unit), ListPolyTree.unit(x));
	}

	// Definition: x.bind(G) = join(x.map(G))
	@Test
	public final void bindShouldEqualJoinMap() {
		assertEquals(treex.bind(G), ListPolyTree.join(treex.map(G)));
		assertEquals(treex.bind(K), ListPolyTree.join(treex.map(K)));
		assertEquals(treex.bind(ListPolyTree::unit), ListPolyTree.join(treex.map(ListPolyTree::unit)));
		assertEquals(treey.bind(G), ListPolyTree.join(treey.map(G)));
	}

	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[][] {
				{ AllListTests.int0, AllListTests.contentsy0, AllListTests.listy0,
					AllListTests.treex0, AllListTests.treey0,
					AllListTests.G0, AllListTests.H0 },
					
					{ AllListTests.int1, AllListTests.contentsy1, AllListTests.listy1,
						AllListTests.treex1, AllListTests.treey1,
						AllListTests.G1, AllListTests.H1 },
						
						{ AllListTests.int2, AllListTests.contentsy2, AllListTests.listy2,
							AllListTests.treex2, AllListTests.treey2,
							AllListTests.G2, AllListTests.H2 },
							
					{ AllListTests.int0, AllListTests.contentsy5, AllListTests.listy5,
						AllListTests.treex0, AllListTests.treey5,
						AllListTests.G0, AllListTests.H0
						},
						
					{ AllListTests.int0, AllListTests.contentsy6, AllListTests.listy6,
						AllListTests.treex0, AllListTests.treey6,
						AllListTests.G0, AllListTests.H0
						}
					});
	}

}
