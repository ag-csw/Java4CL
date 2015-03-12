import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fj.F;
import fj.Function;
import functional.EqEither;
import functional.EqSet;
import functional.EqSetPolyTree;

@RunWith(Parameterized.class)
public class EqSetPolyTreeTest {

	// { AllEqSetTests.expression1,
	// AllEqSetTests.fjexpressions1,
	// AllEqSetTests.singleton1,
	// AllEqSetTests.expressions1,
	// AllEqSetTests.G1,
	// AllEqSetTests.H1 },

	public EqSetPolyTreeTest(
			Integer ix,
			EqSet<Integer> contentsy,
			EqSet<EqEither<Integer, EqSetPolyTree<Integer>>> sety,
			EqSetPolyTree<Integer> treex,
			EqSetPolyTree<Integer> treey,
			F<Integer, EqSetPolyTree<Integer>> g,
			F<EqSetPolyTree<Integer>, EqSetPolyTree<EqSetPolyTree<Integer>>> h) {
		super();
		this.ix = ix;
		this.contentsy = contentsy;
		this.sety = sety;
		this.treex = treex;
		this.treey = treey;
		G = g;
		H = h;
		K = Function.compose(H, G);
		x = EqSetPolyTree.unit(treex);
		setz = EqSet.eqSet();
		treez = EqSetPolyTree.tree(setz);
		//in = null;
		//setn = EqSet.eqSet(in);
		//treen = EqSetPolyTree.tree(setn);
		//n = EqSetPolyTree.unit(treen);
	}

	//public EqEither<Integer, EqSetPolyTree<Integer>> in;
	public Integer ix;
	public EqSet<Integer> contentsy;
	public EqSet<EqEither<Integer, EqSetPolyTree<Integer>>> sety;
	public EqSetPolyTree<Integer> treex;
	public EqSetPolyTree<Integer> treey;

	public F<Integer, EqSetPolyTree<Integer>> G;
	public F<EqSetPolyTree<Integer>, EqSetPolyTree<EqSetPolyTree<Integer>>> H;
	
	public EqSet<EqEither<Integer, EqSetPolyTree<Integer>>> setz;
	//public EqSet<EqEither<Integer, EqSetPolyTree<Integer>>> setn;
	public EqSetPolyTree<Integer> treez;
	//public EqSetPolyTree<Integer> treen;
    public F<Integer, EqSetPolyTree<EqSetPolyTree<Integer>>> K;
	public EqSetPolyTree<EqSetPolyTree<Integer>> x;
	//public EqSetPolyTree<EqSetPolyTree<Integer>> n;

	@Test
	public final void equalsShouldWork() {
		assertEquals(EqEither.unitLeft(ix), EqEither.unitLeft(ix));
		assertEquals(EqSet.eqSet(EqEither.unitLeft(ix)), EqSet.eqSet(EqEither.unitLeft(ix)));
		assertEquals(treex, treex);
		assertEquals(EqSetPolyTree.tree(EqSet.eqSet(EqEither.unitLeft(ix))), EqSetPolyTree.tree(EqSet.eqSet(EqEither.unitLeft(ix))));
		//assertEquals(treex, EqSetPolyTree.unit(ix));
	}

	@Test
	public void singletonTreeShouldBeAsConstructed() {
		assertTrue(treex.contains(ix));
		assertTrue(EqSet.unit(ix).containsAll(treex));
	}

	@Test
	public final void treeShouldBeEqualToOtherEqSetBuiltFromStaticFactorySetMethod() {
		assertEquals(treey, EqSetPolyTree.tree(sety));
	}

	@Test
	public void treeShouldBeAsConstructed() {
		assertTrue(treey.containsAll(contentsy));
		assertTrue(contentsy.containsAll(treey));
	}

	@Test
	public final void singletonShouldContainExpression() {
		assertTrue(treex.contains(ix));
	}

	@Test
	public final void treeWithNullShouldBeMappable() {
		//assertEquals(treen.map(s -> s), treen);
		//assertEquals(treen.bind(EqSetPolyTree::unit), treen);
	}

	@Test
	public final void differentEqSetsShouldNotBeEqual() {
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
		assertEquals(treey.size(), sety.size());
		assertEquals(treex.size(), 1);
		assertEquals(treez.size(), 0);
		//assertEquals(treen.size(), 1);
		assertEquals(x.size(), 1);
	}

	@Test
	public final void firstClassUnitShouldEqualStaticUnit() {
		assertEquals(EqSetPolyTree.unit_().f(ix), EqSetPolyTree.unit(ix));
		assertEquals(EqSetPolyTree.unit_().f(treex), EqSetPolyTree.unit(treex));
		assertEquals(EqSetPolyTree.unit_().f(treey), EqSetPolyTree.unit(treey));
		assertEquals(EqSetPolyTree.unit_().f(x), EqSetPolyTree.unit(x));
	}

	@Test
	public final void firstClassJoinShouldEqualStaticJoin() {
		F<EqSetPolyTree<EqSetPolyTree<Integer>>, EqSetPolyTree<Integer>> joinf = EqSetPolyTree
				.join_();
		assertEquals(joinf.f(x), EqSetPolyTree.join(x));
	}

	
	@Test
	public final void xMapFShouldEqualStaticMapFX() {
		assertEquals(treex.map(G), EqSetPolyTree.map(G, treex));
		assertEquals(treex.map(K), EqSetPolyTree.map(K, treex));
		assertEquals(treey.map(G), EqSetPolyTree.map(G, treey));
		assertEquals(treey.map(K), EqSetPolyTree.map(K, treey));
		assertEquals(treez.map(G), EqSetPolyTree.map(G, treez));
		assertEquals(treez.map(K), EqSetPolyTree.map(K, treez));
		assertEquals(x.map(H), EqSetPolyTree.map(H, x));
	}

	@Test
	public final void xMapFShouldEqualFirstClassMapFX() {
		F<F<Integer,EqSetPolyTree<Integer>>, F<EqSetPolyTree<Integer>, EqSetPolyTree<EqSetPolyTree<Integer>>>> map1 = EqSetPolyTree.map_();
		F<EqSetPolyTree<Integer>, EqSetPolyTree<EqSetPolyTree<Integer>>> mapG = map1.f(G);
		assertEquals(treex.map(G), mapG.f(treex));
		assertEquals(treey.map(G), mapG.f(treey));
		assertEquals(treez.map(G), mapG.f(treez));
	}

	@Test
	public final void xBindFShouldEqualStaticBindFX() {
		assertEquals(treex.bind(G), EqSetPolyTree.bind(G, treex));
		assertEquals(treex.bind(K), EqSetPolyTree.bind(K, treex));
		assertEquals(treex.bind(EqSetPolyTree::unit), EqSetPolyTree.bind(EqSetPolyTree::unit, treex));
		assertEquals(treey.bind(G), EqSetPolyTree.bind(G, treey));
		assertEquals(treey.bind(K), EqSetPolyTree.bind(K, treey));
		assertEquals(treey.bind(EqSetPolyTree::unit), EqSetPolyTree.bind(EqSetPolyTree::unit, treey));
		assertEquals(treez.bind(G), EqSetPolyTree.bind(G, treez));
		assertEquals(treez.bind(K), EqSetPolyTree.bind(K, treez));
		assertEquals(treez.bind(EqSetPolyTree::unit), EqSetPolyTree.bind(EqSetPolyTree::unit, treez));
		assertEquals(x.bind(H), EqSetPolyTree.bind(H, x));
		assertEquals(x.bind(EqSetPolyTree::unit), EqSetPolyTree.bind(EqSetPolyTree::unit, x));
	}

	@Test
	public final void xBindFShouldEqualFirstClassBindFX() {
		F<F<Integer,EqSetPolyTree<Integer>>, F<EqSetPolyTree<Integer>, EqSetPolyTree<Integer>>> bind1 = EqSetPolyTree.bind_();
		F<EqSetPolyTree<Integer>, EqSetPolyTree<Integer>> bindG = bind1.f(G);
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
		assertEquals(EqSetPolyTree.unit(G.f(ix)), EqSetPolyTree.unit(ix).map(G));
		assertEquals(EqSetPolyTree.unit(K.f(ix)), EqSetPolyTree.unit(ix).map(K));
		assertEquals(EqSetPolyTree.unit(H.f(treex)), EqSetPolyTree.unit(treex).map(H));
		assertEquals(EqSetPolyTree.unit(H.f(treey)), EqSetPolyTree.unit(treey).map(H));
		assertEquals(EqSetPolyTree.unit(ix), EqSetPolyTree.unit(ix).map(s -> s));
	}


	// Monad Right Identity Law
	
	//join . fmap join     ≡ join . join
	@Test
	public final void joinMapJoinShouldEqualJoinJoin() {
		EqSetPolyTree<EqSetPolyTree<EqSetPolyTree<Integer>>> ux = EqSetPolyTree.unit(x);
		EqSetPolyTree<EqSetPolyTree<EqSetPolyTree<Integer>>> uy = EqSetPolyTree.unit(EqSetPolyTree.unit(treey));
		EqSetPolyTree<EqSetPolyTree<EqSetPolyTree<Integer>>> uz = EqSetPolyTree.unit(EqSetPolyTree.unit(treez));
		EqSetPolyTree<EqSetPolyTree<EqSetPolyTree<EqSetPolyTree<Integer>>>> uxx = EqSetPolyTree.unit(EqSetPolyTree.unit(x));
		assertEquals( EqSetPolyTree.join(ux.map(EqSetPolyTree.join_())),
			      EqSetPolyTree.join(EqSetPolyTree.join(ux)));
		assertEquals( EqSetPolyTree.join(uy.map(EqSetPolyTree.join_())),
			      EqSetPolyTree.join(EqSetPolyTree.join(uy)));
		assertEquals( EqSetPolyTree.join(uz.map(EqSetPolyTree.join_())),
			      EqSetPolyTree.join(EqSetPolyTree.join(uz)));
		assertEquals( EqSetPolyTree.join(uxx.map(EqSetPolyTree.join_())),
			      EqSetPolyTree.join(EqSetPolyTree.join(uxx)));
		
		EqSetPolyTree<EqSetPolyTree<EqSetPolyTree<Integer>>> wx = ux.insertLeaf(EqSetPolyTree.unit(treey));
		EqSetPolyTree<EqSetPolyTree<EqSetPolyTree<Integer>>> vy = EqSetPolyTree.unit(EqSetPolyTree.unit(treey).insertLeaf(treex).insertBranch(x));
		EqSetPolyTree<EqSetPolyTree<EqSetPolyTree<Integer>>> wy = vy.insertBranch(wx);
		assertEquals( wx.map(EqSetPolyTree.join_()),
			      EqSetPolyTree.join(wx));
		assertEquals( EqSetPolyTree.join(wy.map(EqSetPolyTree.join_())),
				EqSetPolyTree.join(EqSetPolyTree.join(wy)));
	}
	
	// Monad Left Identity Law
	// join . fmap return   ≡ id 
	@Test
	public final void joinMapUnitShouldBeIdentity() {
		assertEquals(EqSetPolyTree.join(treex.map(EqSetPolyTree.unit_())),
		               treex);
		assertEquals(EqSetPolyTree.join(treey.map(EqSetPolyTree.unit_())),
                treey);
		assertEquals(EqSetPolyTree.join(treez.map(EqSetPolyTree.unit_())),
                treez);
		assertEquals(EqSetPolyTree.join(x.map(EqSetPolyTree.unit_())),
	                   x);
	}
	// join . return = id
	@Test
	public final void joinUnitShouldBeIdentity() {
		assertEquals(EqSetPolyTree.join(EqSetPolyTree.unit(treex)), treex);
		assertEquals(EqSetPolyTree.join(EqSetPolyTree.unit(treey)), treey);
		assertEquals(EqSetPolyTree.join(EqSetPolyTree.unit(treez)), treez);
		assertEquals(EqSetPolyTree.join(EqSetPolyTree.unit(x)), x);
		
	}
	
	// redundant, given bind = join(map)
	@Test
	public final void bindUnitShouldEqualIdentity() {
		assertEquals(treex.bind(EqSetPolyTree::unit), treex);
		assertEquals(treey.bind(EqSetPolyTree::unit), treey);
		assertEquals(treez.bind(EqSetPolyTree::unit), treez);
		assertEquals(x.bind(EqSetPolyTree::unit), x);		
	}
	
	
	
	//join . fmap (fmap f) ≡ fmap f . join
	@Test
	public final void joinMapMapFShouldBeMapFJoin() {
		F<    F<Integer, EqSetPolyTree<Integer>>,
		      F<EqSetPolyTree<Integer>, EqSetPolyTree<EqSetPolyTree<Integer>>>  > map1 = EqSetPolyTree.map_();
		F<    F<Integer, EqSetPolyTree<EqSetPolyTree<Integer>>>,
	      F<EqSetPolyTree<Integer>, EqSetPolyTree<EqSetPolyTree<EqSetPolyTree<Integer>>>>  > map2 = EqSetPolyTree.map_();
		F<EqSetPolyTree<Integer>, EqSetPolyTree<EqSetPolyTree<Integer>>> mapG = map1.f(G);
		F<EqSetPolyTree<Integer>, EqSetPolyTree<EqSetPolyTree<EqSetPolyTree<Integer>>>> mapK = map2.f(K);
		EqSetPolyTree<EqSetPolyTree<Integer>> y = EqSetPolyTree.unit(treey);
		EqSetPolyTree<EqSetPolyTree<Integer>> z = EqSetPolyTree.unit(treez);
		assertEquals( EqSetPolyTree.join(x.map(mapG)),
				  EqSetPolyTree.join(x).map(G));
		assertEquals(EqSetPolyTree.join(y.map(mapG)),
				  EqSetPolyTree.join(y).map(G));
		assertEquals(EqSetPolyTree.join(z.map(mapG)),
				  EqSetPolyTree.join(z).map(G));
		assertEquals( EqSetPolyTree.join(x.map(mapK)),
				  EqSetPolyTree.join(x).map(K));
		assertEquals(EqSetPolyTree.join(y.map(mapK)),
				  EqSetPolyTree.join(y).map(K));
		assertEquals(EqSetPolyTree.join(z.map(mapK)),
				  EqSetPolyTree.join(z).map(K));
		
		assertEquals( x.bind(mapG),
				  EqSetPolyTree.join(x).map(G));
		assertEquals( y.bind(mapG),
				  EqSetPolyTree.join(y).map(G));

	}

	//redundant, given bind = join(map) and join(unit) = id
	@Test
	public final void unitBindFShouldEqualF() {
		assertEquals(EqSetPolyTree.unit(ix).bind(EqSetPolyTree::unit), EqSetPolyTree.unit(ix));
		assertEquals(EqSetPolyTree.unit(ix).bind(G), G.f(ix));
		assertEquals(EqSetPolyTree.unit(ix).bind(K), K.f(ix));
		assertEquals(EqSetPolyTree.unit(treex).bind(EqSetPolyTree::unit), EqSetPolyTree.unit(treex));
		assertEquals(EqSetPolyTree.unit(treex).bind(H), H.f(treex));
		assertEquals(EqSetPolyTree.unit(treey).bind(EqSetPolyTree::unit), EqSetPolyTree.unit(treey));
		assertEquals(EqSetPolyTree.unit(treey).bind(H), H.f(treey));
		assertEquals(EqSetPolyTree.unit(x).bind(EqSetPolyTree::unit), EqSetPolyTree.unit(x));
	}

	// Definition: x.bind(G) = join(x.map(G))
	@Test
	public final void bindShouldEqualJoinMap() {
		assertEquals(treex.bind(G), EqSetPolyTree.join(treex.map(G)));
		assertEquals(treex.bind(K), EqSetPolyTree.join(treex.map(K)));
		assertEquals(treex.bind(EqSetPolyTree::unit), EqSetPolyTree.join(treex.map(EqSetPolyTree::unit)));
		assertEquals(treey.bind(G), EqSetPolyTree.join(treey.map(G)));
	}

	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[][] {
				{ AllEqSetTests.int4, AllEqSetTests.contentsy4, AllEqSetTests.sety4,
					AllEqSetTests.treex4, AllEqSetTests.treey4,
					AllEqSetTests.G4, AllEqSetTests.H4 },
					{ AllEqSetTests.int4, AllEqSetTests.contentsy5, AllEqSetTests.sety5,
						AllEqSetTests.treex4, AllEqSetTests.treey5,
						AllEqSetTests.G4, AllEqSetTests.H4
						},
					{ AllEqSetTests.int4, AllEqSetTests.contentsy6, AllEqSetTests.sety6,
						AllEqSetTests.treex4, AllEqSetTests.treey6,
						AllEqSetTests.G4, AllEqSetTests.H4
						}
					});
	}

}
