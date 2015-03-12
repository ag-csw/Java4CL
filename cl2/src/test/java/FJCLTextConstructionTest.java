import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import cl2.CLSentenceOrStatementOrText;
import fj.F;
import fj.Function;
import fj.Ord;
import fj.data.List;
import cl2.FJCLTextConstruction;
import cl2.CLCommentExpression;

@RunWith(Parameterized.class)
public class FJCLTextConstructionTest {



	public FJCLTextConstructionTest(
			CLSentenceOrStatementOrText kex,
			List<CLSentenceOrStatementOrText> listy,
			FJCLTextConstruction<CLSentenceOrStatementOrText> textx,
			FJCLTextConstruction<CLSentenceOrStatementOrText> texty,
			F<CLSentenceOrStatementOrText, FJCLTextConstruction<CLSentenceOrStatementOrText>> g,
			F<FJCLTextConstruction<CLSentenceOrStatementOrText>, FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>>> h) {
		super();
		this.kex = kex;
		this.listy = listy;
		this.textx = textx;
		this.texty = texty;
		this.G = g;
		this.H = h;
		K = Function.compose(H, G);
		x = FJCLTextConstruction.unit(List.nil(), textx);
		textz = FJCLTextConstruction.empty();
		listz = List.nil();
		ken = null;
		listn = List.list(ken);
		textn = FJCLTextConstruction.unit(ken);
		n = FJCLTextConstruction.unit(textn);
		U = FJCLTextConstruction::unit;
		U2 = FJCLTextConstruction::unit;
		J = FJCLTextConstruction::join;	
		}

	public CLSentenceOrStatementOrText ken;
	public CLSentenceOrStatementOrText kex;
	public List<CLSentenceOrStatementOrText> listy;
	public FJCLTextConstruction<CLSentenceOrStatementOrText> textx;
	public FJCLTextConstruction<CLSentenceOrStatementOrText> texty;

	public F<CLSentenceOrStatementOrText, FJCLTextConstruction<CLSentenceOrStatementOrText>> G;
	public F<FJCLTextConstruction<CLSentenceOrStatementOrText>, FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>>> H;
	
	public List<CLSentenceOrStatementOrText> listz;
	public List<CLSentenceOrStatementOrText> listn;
	public FJCLTextConstruction<CLSentenceOrStatementOrText> textz;
	public FJCLTextConstruction<CLSentenceOrStatementOrText> textn;
    public F<CLSentenceOrStatementOrText, FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>>> K;
	public FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>> x;
	public FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>> n;
	public Ord<CLSentenceOrStatementOrText> ordb = Ord.hashEqualsOrd();
	public F<CLSentenceOrStatementOrText, FJCLTextConstruction<CLSentenceOrStatementOrText>> U;
	public F<FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>>, FJCLTextConstruction<FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>>>> U2;
	public F<FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>>, FJCLTextConstruction<CLSentenceOrStatementOrText>> J;

	@Test
	public final void textShouldBeEqualToOtherFJCLTextConstructionBuiltFromStaticVarargsMethod() {
		assertEquals(textx, FJCLTextConstruction.unit(kex));
	}


	@Test
	public final void textShouldBeEqualToOtherFJCLTextConstructionBuiltFromStaticFactoryListMethod() {
		assertEquals(texty, FJCLTextConstruction.text(listy));
	}

	@Test
	public void textShouldBeAsConstructed() {
		assertTrue(texty.containsAll(listy));
		assertTrue(FJCLTextConstruction.text(listy).containsAll(texty.argsList()));
	}

	@Test
	public final void singletonShouldContainExpression() {
		assertTrue(textx.contains(kex));
	}

	@Test
	public final void listShouldBeMappable() {
		assertEquals(listy.map( s -> s).length(), listy.length());
		assertEquals(listz.map( s -> s).length(), listz.length());
		assertEquals(listn.map( s -> s).length(), listn.length());
	}

	@Test
	public final void textWithNullShouldNotBeMappable() {
		assertFalse(textn.map(s -> s).equals(textn));
		assertFalse(textn.bind(FJCLTextConstruction.unit_(List.nil())).equals( textn));
	}

	@Test
	public final void differentFJCLTextConstructionsShouldNotBeEqual() {
		assertFalse(textx.equals(textz)); //different length
		assertFalse(textx.equals(x)); //different type parameter
		assertFalse(textx.equals(kex)); //different class
		assertFalse(textx.equals(G)); //different class
		assertFalse(textz.equals(textn));
		assertFalse(textn.equals(textx));
		assertFalse(textx.equals(null));
	}

	@Test
	public final void textShouldHaveLengthOfList() {
		assertEquals(texty.length(), listy.length());
		assertEquals(textx.length(), 1);
		assertEquals(textz.length(), 0);
		assertEquals(textn.length(), 1);
		assertEquals(x.length(), 1);
	}

	@Test
	public final void firstClassUnitShouldEqualStaticUnit() {
		assertEquals(FJCLTextConstruction.unit_().f(kex), FJCLTextConstruction.unit(kex));
	}

	@Test
	public final void joinUnitUnitShouldEqualUnit() {
		assertEquals(FJCLTextConstruction.join(FJCLTextConstruction.unit(FJCLTextConstruction.unit(kex))), FJCLTextConstruction.unit(kex));
	}

	@Test
	public final void firstClassJoinShouldEqualStaticJoin() {
		assertEquals(J.f(x), FJCLTextConstruction.join(x));
	}

	// Monad Left Identity Law
	@Test
	public final void unitFShouldEqualUnitMapF() {
		assertEquals(FJCLTextConstruction.unit(G.f(kex)), FJCLTextConstruction.unit(kex).map(G));
		assertEquals(FJCLTextConstruction.unit(K.f(kex)), FJCLTextConstruction.unit(kex).map(K));
		assertEquals(FJCLTextConstruction.unit(H.f(textx)), FJCLTextConstruction.unit(textx).map(H));
		assertEquals(FJCLTextConstruction.unit(H.f(texty)), FJCLTextConstruction.unit(texty).map(H));
		assertEquals(FJCLTextConstruction.unit(kex), FJCLTextConstruction.unit(kex).map(s -> s));
	}

	@Test
	public final void xMapFShouldEqualStaticMapFX() {
		assertEquals(textx.map(G), FJCLTextConstruction.map(G, textx));
		assertEquals(textx.map(K), FJCLTextConstruction.map(K, textx));
		assertEquals(texty.map(G), FJCLTextConstruction.map(G, texty));
		assertEquals(texty.map(K), FJCLTextConstruction.map(K, texty));
		assertEquals(textz.map(G), FJCLTextConstruction.map(G, textz));
		assertEquals(textz.map(K), FJCLTextConstruction.map(K, textz));
		assertEquals(x.map(H), FJCLTextConstruction.map(H, x));
	}

	@Test
	public final void xMapFShouldEqualFirstClassMapFX() {
		F<F<CLSentenceOrStatementOrText,FJCLTextConstruction<CLSentenceOrStatementOrText>>, F<FJCLTextConstruction<CLSentenceOrStatementOrText>, FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>>>> map1 = FJCLTextConstruction.map_();
		F<FJCLTextConstruction<CLSentenceOrStatementOrText>, FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>>> mapG = map1.f(G);
		assertEquals(textx.map(G), mapG.f(textx));
		assertEquals(texty.map(G), mapG.f(texty));
		assertEquals(textz.map(G), mapG.f(textz));
	}

	// Functor Law 
	@Test
	public final void mapShouldPreserveIdentityF() {
		assertEquals(textx.map(s -> s), textx);
		assertEquals(texty.map(s -> s), texty);
		assertEquals(x.map(s -> s), x);
	}

	// Monad Associativity Law
	@Test
	public final void mapShouldPreserveComposition() {
		assertEquals(textx.map(G).map(H), textx.map(K));
		assertEquals(texty.map(G).map(H), texty.map(K));
	}


	// Monad Right Identity Law
	@Test
	public final void unitBindFShouldEqualF() {
		assertEquals(FJCLTextConstruction.unit(kex).bind(G), G.f(kex));
		assertEquals(FJCLTextConstruction.unit(textx).bind(H), H.f(textx));
		assertEquals(FJCLTextConstruction.unit(texty).bind(H), H.f(texty));
		assertEquals(FJCLTextConstruction.unit(x).bind(FJCLTextConstruction::unit), FJCLTextConstruction.unit(x));
		assertEquals(FJCLTextConstruction.unit(x).bind(U2), FJCLTextConstruction.unit(x));
	}

	
	// Definition: x.bind(G) = join(x.map(G))
	@Test
	public final void bindShouldEqualJoinMap() {
		assertEquals(textx.bind(G), FJCLTextConstruction.join(textx.map(G)));
		assertEquals(textx.bind(K), FJCLTextConstruction.join(textx.map(K)));
		assertEquals(textx.bind(FJCLTextConstruction::unit), FJCLTextConstruction.join(textx.map(FJCLTextConstruction::unit)));
		assertEquals(texty.bind(G), FJCLTextConstruction.join(texty.map(G)));
	}

	@Test
	public final void xBindFShouldEqualStaticBindFX() {
		assertEquals(textx.bind(G), FJCLTextConstruction.bind(G, textx));
		assertEquals(textx.bind(K), FJCLTextConstruction.bind(K, textx));
		assertEquals(textx.bind(FJCLTextConstruction::unit), FJCLTextConstruction.bind(FJCLTextConstruction::unit, textx));
		assertEquals(texty.bind(G), FJCLTextConstruction.bind(G, texty));
		assertEquals(texty.bind(K), FJCLTextConstruction.bind(K, texty));
		assertEquals(texty.bind(FJCLTextConstruction::unit), FJCLTextConstruction.bind(FJCLTextConstruction::unit, texty));
		assertEquals(textz.bind(G), FJCLTextConstruction.bind(G, textz));
		assertEquals(textz.bind(K), FJCLTextConstruction.bind(K, textz));
		assertEquals(textz.bind(FJCLTextConstruction::unit), FJCLTextConstruction.bind(FJCLTextConstruction::unit, textz));
		assertEquals(x.bind(H), FJCLTextConstruction.bind(H, x));
		assertEquals(x.bind(FJCLTextConstruction::unit), FJCLTextConstruction.bind(FJCLTextConstruction::unit, x));
	}

	@Test
	public final void xBindFShouldEqualFirstClassBindFX() {
		F<F<CLSentenceOrStatementOrText,FJCLTextConstruction<CLSentenceOrStatementOrText>>, F<FJCLTextConstruction<CLSentenceOrStatementOrText>, FJCLTextConstruction<CLSentenceOrStatementOrText>>> bind1 = FJCLTextConstruction.bind_();
		F<FJCLTextConstruction<CLSentenceOrStatementOrText>, FJCLTextConstruction<CLSentenceOrStatementOrText>> bindG = bind1.f(G);
		assertEquals(textx.bind(G), bindG.f(textx));
		assertEquals(texty.bind(G), bindG.f(texty));
		assertEquals(textz.bind(G), bindG.f(textz));
	}

	@Test
	public final void bindUnitShouldEqualIdentity() {
		assertEquals(textx.bind(FJCLTextConstruction::unit), textx);
		assertEquals(texty.bind(FJCLTextConstruction::unit), texty);
		assertEquals(textz.bind(FJCLTextConstruction::unit), textz);
		assertEquals(x.bind(FJCLTextConstruction::unit), x);		
	}
	
	@Test
	public final void bindUnitFShouldEqualMapF() {
		assertEquals(textx.bind(FJCLTextConstruction::unit), textx);
		assertEquals(textx.bind(s -> FJCLTextConstruction.unit(G.f(s))), textx.map(G));
		assertEquals(textx.bind(s -> FJCLTextConstruction.unit(K.f(s))), textx.map(K));
		assertEquals(texty.bind(s -> FJCLTextConstruction.unit(G.f(s))), texty.map(G));
		assertEquals(texty.bind(s -> FJCLTextConstruction.unit(K.f(s))), texty.map(K));
		assertEquals(x.bind(s -> FJCLTextConstruction.unit(H.f(s))), x.map(H));
	}


	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[][] {
				{ AllCLTests.expression0, AllCLTests.expressions0,
					AllCLTests.singleton0, AllCLTests.text0,
					AllCLTests.G0, AllCLTests.H0 }
					});
	}

}
