import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fj.F;
import fj.Function;
import fj.Ord;
import fj.data.Set;
import functional.EqSet;

@RunWith(Parameterized.class)
public class EqSetTest {

	// { AllEqSetTests.expression1,
	// AllEqSetTests.fjexpressions1,
	// AllEqSetTests.singleton1,
	// AllEqSetTests.expressions1,
	// AllEqSetTests.G1,
	// AllEqSetTests.H1 },

	public EqSetTest(
			EqSet<Integer> kex,
			Set<EqSet<Integer>> sety,
			EqSet<EqSet<Integer>> eqsetx,
			EqSet<EqSet<Integer>> eqsety,
			F<EqSet<Integer>, EqSet<EqSet<Integer>>> g,
			F<EqSet<EqSet<Integer>>, EqSet<EqSet<EqSet<Integer>>>> h) {
		super();
		this.kex = kex;
		this.sety = sety;
		this.eqsetx = eqsetx;
		this.eqsety = eqsety;
		G = g;
		H = h;
		K = Function.compose(H, G);
		x = EqSet.unit(eqsetx);
		eqsetz = EqSet.eqSet();
		ordb = Ord.hashEqualsOrd();
		setz = Set.empty(ordb);
		//ken = null;
		//setn = Set.set(ordb, ken);
		//eqsetn = EqSet.eqSet(ken);
		//n = EqSet.unit(eqsetn);
	}

	//public EqSetKnowledgeExpression ken;
	public EqSet<Integer> kex;
	public Set<EqSet<Integer>> sety;
	public EqSet<EqSet<Integer>> eqsetx;
	public EqSet<EqSet<Integer>> eqsety;

	public F<EqSet<Integer>, EqSet<EqSet<Integer>>> G;
	public F<EqSet<EqSet<Integer>>, EqSet<EqSet<EqSet<Integer>>>> H;
	
	public Set<EqSet<Integer>> setz;
	//public Set<EqSetKnowledgeExpression> setn;
	public EqSet<EqSet<Integer>> eqsetz;
	//public EqSet<EqSetKnowledgeExpression> eqsetn;
    public F<EqSet<Integer>, EqSet<EqSet<EqSet<Integer>>>> K;
	public EqSet<EqSet<EqSet<Integer>>> x;
	//public EqSet<EqSet<EqSetKnowledgeExpression>> n;
	public Ord<EqSet<Integer>> ordb = Ord.hashEqualsOrd();

	@Test
	public final void eqsetShouldBeEqualToOtherEqSetBuiltFromStaticVarargsMethod() {
		assertEquals(eqsetx, EqSet.eqSet(kex));
	}


	@Test
	public final void eqsetShouldBeEqualToOtherEqSetBuiltFromStaticFactorySetMethod() {
		assertEquals(eqsety, EqSet.eqSet(sety));
	}

	@Test
	public void eqsetShouldBeAsConstructed() {
		assertTrue(eqsety.containsAll(sety));
		assertTrue(EqSet.eqSet(sety).containsAll(eqsety));
	}

	@Test
	public final void singletonShouldContainExpression() {
		assertTrue(eqsetx.contains(kex));
	}

	@Test
	public final void setShouldBeMappable() {
		assertEquals(sety.map(ordb, s -> s).size(), sety.size());
		assertEquals(setz.map(ordb, s -> s).size(), setz.size());
		//assertEquals(setn.map(ordb, s -> s).size(), setn.size());
	}

	@Test
	public final void eqsetWithNullShouldNotBeMappable() {
		//assertEquals(eqsetn.map(s -> s), eqsetn);
		//assertEquals(eqsetn.bind(EqSet::unit), eqsetn);
	}

	@Test
	public final void differentEqSetsShouldNotBeEqual() {
		assertFalse(eqsetx.equals(eqsetz)); //different size
		assertFalse(eqsetx.equals(x)); //different type parameter
		assertFalse(eqsetx.equals(kex)); //different class
		assertFalse(eqsetx.equals(G)); //different class
		//assertFalse(eqsetz.equals(eqsetn));
		//assertFalse(eqsetn.equals(eqsetx));
		assertFalse(eqsetx.equals(null));
	}

	@Test
	public final void eqsetShouldHaveSizeOfSet() {
		assertEquals(eqsety.size(), sety.size());
		assertEquals(eqsetx.size(), 1);
		assertEquals(eqsetz.size(), 0);
		//assertEquals(eqsetn.size(), 1);
		assertEquals(x.size(), 1);
	}

	@Test
	public final void firstClassUnitShouldEqualStaticUnit() {
		assertEquals(EqSet.unit_().f(kex), EqSet.unit(kex));
	}

	@Test
	public final void joinUnitUnitShouldEqualUnit() {
		assertEquals(EqSet.join(EqSet.unit(EqSet.unit(kex))), EqSet.unit(kex));
	}

	@Test
	public final void firstClassJoinShouldEqualStaticJoin() {
		F<EqSet<EqSet<EqSet<Integer>>>, EqSet<EqSet<Integer>>> joinf = EqSet
				.join_();
		assertEquals(joinf.f(x), EqSet.join(x));
	}

	// Monad Left Identity Law
	@Test
	public final void unitFShouldEqualUnitMapF() {
		assertEquals(EqSet.unit(G.f(kex)), EqSet.unit(kex).map(G));
		assertEquals(EqSet.unit(K.f(kex)), EqSet.unit(kex).map(K));
		assertEquals(EqSet.unit(H.f(eqsetx)), EqSet.unit(eqsetx).map(H));
		assertEquals(EqSet.unit(H.f(eqsety)), EqSet.unit(eqsety).map(H));
		assertEquals(EqSet.unit(kex), EqSet.unit(kex).map(s -> s));
	}

	@Test
	public final void xMapFShouldEqualStaticMapFX() {
		assertEquals(eqsetx.map(G), EqSet.map(G, eqsetx));
		assertEquals(eqsetx.map(K), EqSet.map(K, eqsetx));
		assertEquals(eqsety.map(G), EqSet.map(G, eqsety));
		assertEquals(eqsety.map(K), EqSet.map(K, eqsety));
		assertEquals(eqsetz.map(G), EqSet.map(G, eqsetz));
		assertEquals(eqsetz.map(K), EqSet.map(K, eqsetz));
		assertEquals(x.map(H), EqSet.map(H, x));
	}

	@Test
	public final void xMapFShouldEqualFirstClassMapFX() {
		F<F<EqSet<Integer>,EqSet<EqSet<Integer>>>, F<EqSet<EqSet<Integer>>, EqSet<EqSet<EqSet<Integer>>>>> map1 = EqSet.map_();
		F<EqSet<EqSet<Integer>>, EqSet<EqSet<EqSet<Integer>>>> mapG = map1.f(G);
		assertEquals(eqsetx.map(G), mapG.f(eqsetx));
		assertEquals(eqsety.map(G), mapG.f(eqsety));
		assertEquals(eqsetz.map(G), mapG.f(eqsetz));
	}

	// Functor Law 
	@Test
	public final void mapShouldPreserveIdentityF() {
		assertEquals(eqsetx.map(s -> s), eqsetx);
		assertEquals(eqsety.map(s -> s), eqsety);
		assertEquals(x.map(s -> s), x);
	}

	// Monad Associativity Law
	@Test
	public final void mapShouldPreserveComposition() {
		assertEquals(eqsetx.map(G).map(H), eqsetx.map(K));
		assertEquals(eqsety.map(G).map(H), eqsety.map(K));
	}


	// Monad Right Identity Law
	@Test
	public final void unitBindFShouldEqualF() {
		assertEquals(EqSet.unit(kex).bind(G), G.f(kex));
		assertEquals(EqSet.unit(eqsetx).bind(H), H.f(eqsetx));
		assertEquals(EqSet.unit(eqsety).bind(H), H.f(eqsety));
		assertEquals(EqSet.unit(x).bind(EqSet::unit), EqSet.unit(x));
	}

	// Definition: x.bind(G) = join(x.map(G))
	@Test
	public final void bindShouldEqualJoinMap() {
		assertEquals(eqsetx.bind(G), EqSet.join(eqsetx.map(G)));
		assertEquals(eqsetx.bind(K), EqSet.join(eqsetx.map(K)));
		assertEquals(eqsetx.bind(EqSet::unit), EqSet.join(eqsetx.map(EqSet::unit)));
		assertEquals(eqsety.bind(G), EqSet.join(eqsety.map(G)));
	}

	@Test
	public final void xBindFShouldEqualStaticBindFX() {
		assertEquals(eqsetx.bind(G), EqSet.bind(G, eqsetx));
		assertEquals(eqsetx.bind(K), EqSet.bind(K, eqsetx));
		assertEquals(eqsetx.bind(EqSet::unit), EqSet.bind(EqSet::unit, eqsetx));
		assertEquals(eqsety.bind(G), EqSet.bind(G, eqsety));
		assertEquals(eqsety.bind(K), EqSet.bind(K, eqsety));
		assertEquals(eqsety.bind(EqSet::unit), EqSet.bind(EqSet::unit, eqsety));
		assertEquals(eqsetz.bind(G), EqSet.bind(G, eqsetz));
		assertEquals(eqsetz.bind(K), EqSet.bind(K, eqsetz));
		assertEquals(eqsetz.bind(EqSet::unit), EqSet.bind(EqSet::unit, eqsetz));
		assertEquals(x.bind(H), EqSet.bind(H, x));
		assertEquals(x.bind(EqSet::unit), EqSet.bind(EqSet::unit, x));
	}

	@Test
	public final void xBindFShouldEqualFirstClassBindFX() {
		F<F<EqSet<Integer>,EqSet<EqSet<Integer>>>, F<EqSet<EqSet<Integer>>, EqSet<EqSet<Integer>>>> bind1 = EqSet.bind_();
		F<EqSet<EqSet<Integer>>, EqSet<EqSet<Integer>>> bindG = bind1.f(G);
		assertEquals(eqsetx.bind(G), bindG.f(eqsetx));
		assertEquals(eqsety.bind(G), bindG.f(eqsety));
		assertEquals(eqsetz.bind(G), bindG.f(eqsetz));
	}

	@Test
	public final void bindUnitShouldEqualIdentity() {
		assertEquals(eqsetx.bind(EqSet::unit), eqsetx);
		assertEquals(eqsety.bind(EqSet::unit), eqsety);
		assertEquals(eqsetz.bind(EqSet::unit), eqsetz);
		assertEquals(x.bind(EqSet::unit), x);		
	}
	
	@Test
	public final void bindUnitFShouldEqualMapF() {
		assertEquals(eqsetx.bind(EqSet::unit), eqsetx);
		assertEquals(eqsetx.bind(s -> EqSet.unit(G.f(s))), eqsetx.map(G));
		assertEquals(eqsetx.bind(s -> EqSet.unit(K.f(s))), eqsetx.map(K));
		assertEquals(eqsety.bind(s -> EqSet.unit(G.f(s))), eqsety.map(G));
		assertEquals(eqsety.bind(s -> EqSet.unit(K.f(s))), eqsety.map(K));
		assertEquals(x.bind(s -> EqSet.unit(H.f(s))), x.map(H));
	}


	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[][] {
				{ AllEqSetTests.kex0, AllEqSetTests.fjsety0,
					AllEqSetTests.eqsetx0, AllEqSetTests.eqsety0,
					AllEqSetTests.G0, AllEqSetTests.H0 }
					});
	}

}
