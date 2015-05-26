import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fj.F;
import fj.Function;
import fj.data.List;
import fj.data.State;
import functional.EqState;

@RunWith(Parameterized.class)
public class EqStateTest {

	public EqStateTest(
			Integer intx,
			List<Integer> stack,
			State<List<Integer>,Integer> statey,
			EqState<List<Integer>,Integer> eqstatex,
			EqState<List<Integer>,Integer> eqstatey,
			F<Integer, ?> f,
			F<Integer, EqState<List<Integer>,Integer>> g,
			F<EqState<List<Integer>,Integer>, EqState<List<Integer>,EqState<List<Integer>,Integer>>> h) {
		super();
		this.intx = intx;
		this.stack = stack;
		this.statey = statey;
		this.eqstatex = eqstatex;
		this.eqstatey = eqstatey;
		D = s -> 2*s;
		F = f;
		DF = Function.compose(F, D);
		G = g;
		H = h;
		K = Function.compose(H, G);
		x = EqState.unit(eqstatex);
		// Integer ken = null;
		// State<List<Integer>, Integer> setn = State.constant(ken);
		//eqsetn = EqState.eqState(ken);
		//n = EqState.unit(eqsetn);
		id = s -> s;
		statez = statey.map(id);
	}

	//public Integer ken;
	public Integer intx;
	public List<Integer> stack;
	public State<List<Integer>,Integer> statey;
	public EqState<List<Integer>,Integer> eqstatex;
	public EqState<List<Integer>,Integer> eqstatey;

	public F<Integer, Integer> D;
	public F<Integer, ?> F;
	public F<Integer, ?> DF;
	public F<Integer, EqState<List<Integer>,Integer>> G;
	public F<EqState<List<Integer>,Integer>, EqState<List<Integer>,EqState<List<Integer>,Integer>>> H;
	
	F<Integer, Integer> id;
	public State<List<Integer>,Integer> statez;
	//public EqState<List<Integer>,Integer> eqsetn;
    public F<Integer, EqState<List<Integer>,EqState<List<Integer>,Integer>>> K;
	public EqState<List<Integer>,EqState<List<Integer>,Integer>> x;
	//public EqState<List<Integer>,EqState<List<Integer>,Integer>> n;

	@Test
	public final void eqsetShouldBeEqualToOtherEqStateBuiltFromStaticVarargsMethod() {
		assertEqualsEqState(eqstatex, EqState.unit(intx), stack);
	}


	@Test
	public final void eqsetShouldBeEqualToOtherEqStateBuiltFromStaticFactoryStateMethod() {
	    assertEqualsEqState(eqstatey, EqState.eqState(statey), stack);
	}

	@Test
	public void eqsetShouldBeAsConstructed() {
		assertTrue(eqstatey.run(stack)._1().equals(statey.run(stack)._1()));
		assertTrue(eqstatey.run(stack)._2().equals(statey.run(stack)._2()));
	}

	@Test
	public final void singletonShouldContainExpression() {
		assertTrue(eqstatex.run(stack)._2().equals(intx));
	}

	@Test
	public final void stateShouldBeMappable() {
		assertTrue(statey.run(stack)._1().equals(statez.run(stack)._1()));
		assertTrue(statey.run(stack)._2().equals(statez.run(stack)._2()));
	}

	@Test
	public final void eqsetWithNullShouldNotBeMappable() {
		//assertEqualsEqState(eqsetn.map(s -> s), eqsetn);
		//assertEqualsEqState(eqsetn.bind(EqState::unit), eqsetn);
	}

	@Test
	public final void differentEqStatesShouldNotBeEqual() {
		assertFalse(eqstatex.equals(eqstatey)); //different size
		assertFalse(eqstatex.equals(x)); //different type parameter
		assertFalse(eqstatex.equals(intx)); //different class
		assertFalse(eqstatex.equals(G)); //different class
		//assertFalse(eqsetz.equals(eqsetn));
		//assertFalse(eqsetn.equals(eqstatex));
		assertFalse(eqstatex.equals(null));
	}

	@Test
	public final void firstClassUnitShouldEqualStaticUnit() {
		assertEqualsEqState(EqState.unit_().f(intx), EqState.unit(intx), stack);
	}

	@Test
	public final void joinUnitUnitShouldEqualUnit() {
		assertEqualsEqState(EqState.join(EqState.unit(EqState.unit(intx))), EqState.unit(intx), stack);
	}

	@Test
	public final void firstClassJoinShouldEqualStaticJoin() {
		F<EqState<List<Integer>,EqState<List<Integer>,Integer>>, EqState<List<Integer>,Integer>> joinf = EqState
				.join_();
		assertEqualsEqState(joinf.f(x), EqState.join(x), stack);
	}

	// Monad Left Identity Law
	@Test
	public final void unitFShouldEqualUnitMapF() {
		assertEqualsEqState(EqState.unit(D.f(intx)), EqState.unit(intx).map(D), stack);
		//assertEqualsEqState(EqState.unit(K.f(intx)), EqState.unit(intx).map(K), stack);
		//assertEqualsEqState(EqState.unit(H.f(eqstatex)), EqState.unit(eqstatex).map(H), stack);
		//assertEqualsEqState(EqState.unit(H.f(eqstatey)), EqState.unit(eqstatey).map(H), stack);
		//assertEqualsEqState(EqState.unit(intx), EqState.unit(intx).map(s -> s), stack);
	}

	@Test
	public final void xMapFShouldEqualStaticMapFX() {
		assertEqualsEqState(eqstatex.map(F), EqState.map(F, eqstatex), stack);
		assertEqualsEqState(eqstatey.map(F), EqState.map(F, eqstatey), stack);
	}

	@Test
	public final void xMapFShouldEqualFirstClassMapFX() {
		F<F<Integer, Integer>, F<EqState<List<Integer>,Integer>, EqState<List<Integer>,Integer> >> map1 = EqState.map_();
		F<EqState<List<Integer>,Integer>, EqState<List<Integer>,Integer>> mapF = map1.f(D);
		assertEqualsEqState(eqstatex.map(D), mapF.f(eqstatex), stack);
		assertEqualsEqState(eqstatey.map(D), mapF.f(eqstatey), stack);
	}

	// Functor Law 
	@Test
	public final void mapShouldPreserveIdentityF() {
		assertEqualsEqState(eqstatex.map(s -> s), eqstatex, stack);
		assertEqualsEqState(eqstatey.map(s -> s), eqstatey, stack);
		assertEqualsEqState(x.map(s -> s), x, stack);
	}

	// Monad Associativity Law
	@Test
	public final void mapShouldPreserveComposition() {
		assertEqualsEqState(eqstatex.map(D).map(F), eqstatex.map(DF), stack);
		assertEqualsEqState(eqstatey.map(D).map(F), eqstatey.map(DF), stack);
	}


	// Monad Right Identity Law
	@Test
	public final void unitBindFShouldEqualF() {
		EqState<List<Integer>, Integer> stx = EqState.unit(intx);
		EqState<List<Integer>, EqState<List<Integer>,Integer>> sty = EqState.unit(eqstatey);
		EqState<List<Integer>, Integer> xG = stx.bind(G);
		assertEqualsEqState(xG, G.f(intx), stack);
		assertEqualsEqState(x.bind(H), H.f(eqstatex), stack);
		assertEqualsEqState(sty.bind(H), H.f(eqstatey), stack);
		assertEqualsEqState(EqState.unit(x).bind(y -> EqState.unit(y)), EqState.unit(x), stack);
	}

	// Definition: x.bind(G) = join(x.map(G))
	@Test
	public final void bindShouldEqualJoinMap() {
	    assertEqualsEqState(eqstatex.bind(G), EqState.join(eqstatex.map(G)), stack);
		//assertEqualsEqState(eqstatex.bind(K), EqState.join(eqstatex.map(K)), stack);
		//assertEqualsEqState(eqstatex.bind(EqState::unit), EqState.join(eqstatex.map(EqState::unit)), stack);
	    assertEqualsEqState(eqstatey.bind(G), EqState.join(eqstatey.map(G)), stack);
	}

	@Test
	public final void xBindFShouldEqualStaticBindFX() {
		assertEqualsEqState(eqstatex.bind(G), EqState.bind(G, eqstatex), stack);
		assertEqualsEqState(eqstatex.bind(K).run(stack)._2(), EqState.bind(K, eqstatex).run(stack)._2(), stack);
		assertEquals(eqstatex.bind(K).run(stack)._1(), EqState.bind(K, eqstatex).run(stack)._1());
		//assertEqualsEqState(eqstatex.bind(EqState::unit), EqState.bind(EqState::unit, eqstatex), stack);
		assertEqualsEqState(eqstatey.bind(G), EqState.bind(G, eqstatey), stack);
		assertEqualsEqStateEqState(eqstatey.bind(K), EqState.bind(K, eqstatey), stack, stack);
		//assertEqualsEqState(eqstatey.bind(EqState::unit), EqState.bind(EqState::unit, eqstatey), stack);
		assertEqualsEqState(x.bind(H), EqState.bind(H, x), stack);
		assertEqualsEqState(x.bind(EqState::unit), EqState.bind(y -> EqState.unit(y), x), stack);
	}

	@Test
	public final void xBindFShouldEqualFirstClassBindFX() {
		F<F<Integer,EqState<List<Integer>,Integer>>, F<EqState<List<Integer>,Integer>, EqState<List<Integer>,Integer>>> bind1 = EqState.bind_();
		F<EqState<List<Integer>,Integer>, EqState<List<Integer>,Integer>> bindG = bind1.f(G);
		assertEqualsEqState(eqstatex.bind(G), bindG.f(eqstatex), stack);
		assertEqualsEqState(eqstatey.bind(G), bindG.f(eqstatey), stack);
	}

	@Test
	public final void bindUnitShouldEqualIdentity() {
		assertEqualsEqState(eqstatex.bind(y -> EqState.unit(y)), eqstatex, stack);
		assertEqualsEqState(eqstatey.bind(y -> EqState.unit(y)), eqstatey, stack);
		assertEqualsEqState(x.bind(y -> EqState.unit(y)), x, stack);		
	}
	
	@Test
	public final void bindUnitFShouldEqualMapF() {
		assertEqualsEqState(eqstatex.bind(y -> EqState.unit(y)), eqstatex, stack);
		assertEqualsEqState(eqstatex.bind(s -> EqState.unit(D.f(s))), eqstatex.map(D), stack);
		assertEqualsEqState(eqstatex.bind(s -> EqState.unit(F.f(s))), eqstatex.map(F), stack);
		assertEqualsEqState(eqstatey.bind(y -> EqState.unit(y)), eqstatey, stack);
		assertEqualsEqState(eqstatey.bind(s -> EqState.unit(D.f(s))), eqstatey.map(D), stack);
		assertEqualsEqState(eqstatey.bind(s -> EqState.unit(F.f(s))), eqstatey.map(F), stack);
	}


	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[][] {
				{ AllEqStateTests.int0, AllEqStateTests.stack0, AllEqStateTests.statey0,
					AllEqStateTests.eqstatex0, AllEqStateTests.eqstatey0,
					AllEqStateTests.f0, AllEqStateTests.g0, AllEqStateTests.h0 }
					});
	}
	
	public static <S> void assertEqualsEqState(EqState<S, ?> x, EqState<S, ?> y, S s){
		assertEquals(x.run(s)._2(), y.run(s)._2());
		S s2 = x.run(s)._1();
		S s3 = y.run(s)._1();
		assertEquals(x.run(s2)._2(), y.run(s3)._2());
	}

	public static <S, T> void assertEqualsEqStateEqState(EqState<List<Integer>,EqState<List<Integer>, Integer>> x, EqState<List<Integer>, EqState<List<Integer>, Integer>> y, List<Integer> stack2, List<Integer> stack3){
		assertEquals(x.run(stack2)._1(), y.run(stack2)._1());
		EqState<List<Integer>, Integer> x2 = x.run(stack2)._2();
		EqState<List<Integer>, Integer> y2 = y.run(stack2)._2();
		assertEqualsEqState(x2, y2, stack3);
	}

}
