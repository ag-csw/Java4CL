import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import api4kbc.EqSetKnowledgeExpression;
import fj.F;
import fj.Function;
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
			EqSetKnowledgeExpression kex,
			Set<EqSetKnowledgeExpression> sety,
			EqSet<EqSetKnowledgeExpression> eqsetx,
			EqSet<EqSetKnowledgeExpression> eqsety,
			F<EqSetKnowledgeExpression, EqSet<EqSetKnowledgeExpression>> g,
			F<EqSet<EqSetKnowledgeExpression>, EqSet<EqSet<EqSetKnowledgeExpression>>> h) {
		super();
		this.kex = kex;
		this.sety = sety;
		this.eqsetx = eqsetx;
		this.eqsety = eqsety;
		G = g;
		H = h;
		x = EqSet.unit(eqsetx);
	}

	public EqSetKnowledgeExpression kex;
	public Set<EqSetKnowledgeExpression> sety;
	public EqSet<EqSetKnowledgeExpression> eqsetx;
	public EqSet<EqSetKnowledgeExpression> eqsety;

	public F<EqSetKnowledgeExpression, EqSet<EqSetKnowledgeExpression>> G;
	public F<EqSet<EqSetKnowledgeExpression>, EqSet<EqSet<EqSetKnowledgeExpression>>> H;

	public EqSet<EqSet<EqSetKnowledgeExpression>> x;

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
	public final void eqsetShouldHaveSizeOfSet() {
		assertEquals(eqsety.size(), sety.size());
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
		F<EqSet<EqSet<EqSetKnowledgeExpression>>, EqSet<EqSetKnowledgeExpression>> joinf = EqSet
				.join_();
		assertEquals(joinf.f(x), EqSet.join(x));
	}

	@Test
	public final void unitFShouldEqualUnitMapF() {
		assertEquals(EqSet.unit(G.f(kex)), EqSet.unit(kex).map(G));
	}

	@Test
	public final void mapShouldPreserveComposition() {
		assertEquals(eqsetx.map(G).map(H), eqsetx.map(Function.compose(H, G)));
	}

	@Test
	public final void mapShouldPreserveIdentityF() {
		assertEquals(eqsetx.map(s -> s), eqsetx);
	}

	@Test
	public final void unitBindFShouldEqualF() {
		assertEquals(EqSet.unit(kex).bind(G), G.f(kex));

	}

	@Test
	public final void bindUnitFShouldEqualMapF() {
		assertEquals(eqsety.bind(s -> EqSet.unit(G.f(s))), eqsety.map(G));
	}


	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[][] {
				{ AllEqSetTests.expression0, AllEqSetTests.fjexpressions0,
					AllEqSetTests.singleton0, AllEqSetTests.expressions0,
					AllEqSetTests.G0, AllEqSetTests.H0 },
				{ AllEqSetTests.expression1, AllEqSetTests.fjexpressions1,
						AllEqSetTests.singleton1, AllEqSetTests.expressions1,
						AllEqSetTests.G1, AllEqSetTests.H1 },
						{ AllEqSetTests.stexpr2, AllEqSetTests.fjexpressions2,
							AllEqSetTests.singleton2, AllEqSetTests.expressions2,
							AllEqSetTests.G2, AllEqSetTests.H2 }, 
						{ AllEqSetTests.stexpr2, AllEqSetTests.fjexpressions2,
							AllEqSetTests.singleton2, AllEqSetTests.expressions2,
							AllEqSetTests.G3, AllEqSetTests.H3 } 
					});
	}

}
