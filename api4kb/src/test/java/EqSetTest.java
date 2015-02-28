import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import api4kbc.EqSetKnowledgeExpression;
import fj.Equal;
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
	public final void eqsetShouldBeEqualToEqSetBuiltFromStaticFactoryMethod() {
		assertEquals(eqsetx, EqSet.eqSet(eqsetx.set()));
	}

	@Test
	public void eqsetShouldBeAsConstructed() {
		Equal<EqSetKnowledgeExpression> eqa = Equal.anyEqual();
		Equal<Set<EqSetKnowledgeExpression>> eqseta = Equal.setEqual(eqa);
		assertTrue(eqseta.eq(eqsety.set(), sety));
		assertEquals(EqSet.set(eqsety), eqsety.set());
		F<EqSet<EqSetKnowledgeExpression>, Set<EqSetKnowledgeExpression>> setf = EqSet
				.set_();
		assertEquals(setf.f(eqsety), eqsety.set());
	}

	@Test
	public final void eqsetyShouldHaveMemberkex() {
		assertTrue(eqsety.member(kex));
	}

	@Test
	public final void eqsetShouldHaveSizeOfSet() {
		assertEquals(eqsety.size(), eqsety.set().size());
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
	public final void unitBindFShouldEqualF() {
		assertEquals(EqSet.unit(kex).bind(G), G.f(kex));

	}

	@Test
	public final void unitFShouldEqualUnitMapF() {
		assertEquals(EqSet.unit(G.f(kex)), EqSet.unit(kex).map(G));
	}

	@Test
	public final void bindUnitFShouldEqualMapF() {
		assertEquals(eqsety.bind(s -> EqSet.unit(G.f(s))), eqsety.map(G));
	}

	@Test
	public final void mapShouldPreserveComposition() {
		assertEquals(eqsetx.map(G).map(H), eqsetx.map(Function.compose(H, G)));
	}

	@Test
	public final void mapShouldPreserveIdentityF() {
		assertEquals(eqsetx.map(s -> s), eqsetx);
	}

	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[][] {
				{ AllEqSetTests.expression1, AllEqSetTests.fjexpressions1,
						AllEqSetTests.singleton1, AllEqSetTests.expressions1,
						AllEqSetTests.G1, AllEqSetTests.H1 },
				{ AllEqSetTests.stexpr0, AllEqSetTests.fjexpressions2,
						AllEqSetTests.singleton2, AllEqSetTests.expressions2,
						AllEqSetTests.G2, AllEqSetTests.H2 } });
	}

}
