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
import functional.EqEither;
import functional.EqSet;
import cl2.FJCLTextConstructionCSet;
import cl2.CLCommentExpression;

@RunWith(Parameterized.class)
public class FJCLTextConstructionCSetTest {





	public FJCLTextConstructionCSetTest(
			CLSentenceOrStatementOrText kex,
			List<CLSentenceOrStatementOrText> contentsy,
			List<EqEither<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> listy,
			FJCLTextConstructionCSet<CLSentenceOrStatementOrText> textx,
			FJCLTextConstructionCSet<CLSentenceOrStatementOrText> texty,
			F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> g,
			F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> h,
			EqSet<CLCommentExpression> comments) {
		super();
		this.kex = kex;
		this.contentsy = contentsy;
		this.listy = listy;
		this.textx = textx;
		this.texty = texty;
		this.G = g;
		this.H = h;
		this.comments = comments;
		K = Function.compose(H, G);
		C = s -> s.insertComments(comments);
		//Gc = Function.compose(s -> s.insertComments(comments), Function.compose(G, C));
		//Kc = Function.compose(s -> s.insertComments(comments), K);
		x = FJCLTextConstructionCSet.unit(comments, textx);
		textz = FJCLTextConstructionCSet.empty().insertComments(comments);
		listz = List.nil();
		ken = null;
		listn = List.list(ken);
		textn = FJCLTextConstructionCSet.unit(comments, ken);
		n = FJCLTextConstructionCSet.unit(comments, textn);
		U = FJCLTextConstructionCSet.unit_(comments);
		U2 = FJCLTextConstructionCSet.unit_(comments);
		J = FJCLTextConstructionCSet::join;	
		}

	public CLSentenceOrStatementOrText ken;
	public CLSentenceOrStatementOrText kex;
	public List<CLSentenceOrStatementOrText> contentsy;
	public List<EqEither<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> listy;
	public FJCLTextConstructionCSet<CLSentenceOrStatementOrText> textx;
	public FJCLTextConstructionCSet<CLSentenceOrStatementOrText> texty;

	public F<CLSentenceOrStatementOrText, CLSentenceOrStatementOrText> C;
	public F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> G;
	public F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> Gc;
	public F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> H;
	
	public List<CLSentenceOrStatementOrText> listz;
	public List<CLSentenceOrStatementOrText> listn;
	public FJCLTextConstructionCSet<CLSentenceOrStatementOrText> textz;
	public FJCLTextConstructionCSet<CLSentenceOrStatementOrText> textn;
    public F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> K;
    public F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> Kc;
    public FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> x;
	public FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> n;
	public Ord<CLSentenceOrStatementOrText> ordb = Ord.hashEqualsOrd();
	public F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> U;
	public F<FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>>> U2;
	public F<FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> J;
	public EqSet<CLCommentExpression> comments;

	@Test
	public final void equals() {
		EqSet<CLCommentExpression> emptycomments1 = EqSet.empty();
		EqSet<CLCommentExpression> emptycomments2 = EqSet.empty();
		EqEither<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> either1 = EqEither.unitLeft(kex);
		EqEither<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> either2 = EqEither.unitLeft(kex);
		List<CLSentenceOrStatementOrText> contents1 = List.cons(kex, List.nil());
		List<CLSentenceOrStatementOrText> contents2 = List.cons(kex, List.nil());		
		assertEquals(emptycomments1, emptycomments2);
		assertEquals(either1, either2);
		assertEquals(contents1, contents2);
	}


	@Test
	public final void textShouldBeEqualToOtherFJCLTextConstructionCSetBuiltFromStaticVarargsMethod() {
		assertEquals(textx, FJCLTextConstructionCSet.unit(comments, kex));
	}


	@Test
	public final void textShouldBeEqualToOtherFJCLTextConstructionCSetBuiltFromStaticFactoryListMethod() {
		assertEquals(texty, FJCLTextConstructionCSet.text(comments, listy));
	}

	@Test
	public void textShouldBeAsConstructed() {
		assertTrue(texty.containsAll(contentsy));
		assertTrue(FJCLTextConstructionCSet.fromLeftList(contentsy).containsAll(texty.toLeftList()));
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
		//assertFalse(textn.bind(FJCLTextConstructionCSet.unit_(EqSet.eqSet())).equals( textn));
	}

	@Test
	public final void differentFJCLTextConstructionCSetsShouldNotBeEqual() {
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
		assertEquals(FJCLTextConstructionCSet.unit_().f(kex), FJCLTextConstructionCSet.unit(kex));
	}

	@Test
	public final void joinUnitUnitShouldEqualUnit() {
		assertEquals(FJCLTextConstructionCSet.join(FJCLTextConstructionCSet.unit(FJCLTextConstructionCSet.unit(kex))), FJCLTextConstructionCSet.unit(kex));
		assertEquals(FJCLTextConstructionCSet.join(FJCLTextConstructionCSet.unit(comments, FJCLTextConstructionCSet.unit(comments, kex))), FJCLTextConstructionCSet.unit(comments, kex));
	}

	@Test
	public final void firstClassJoinShouldEqualStaticJoin() {
		assertEquals(J.f(x), FJCLTextConstructionCSet.join(x));
	}

	// Monad Left Identity Law
	@Test
	public final void xMapFShouldEqualStaticMapFX() {
		assertEquals(textx.map(G), FJCLTextConstructionCSet.map(G, textx));
		assertEquals(textx.map(K), FJCLTextConstructionCSet.map(K, textx));
		assertEquals(texty.map(G), FJCLTextConstructionCSet.map(G, texty));
		assertEquals(texty.map(K), FJCLTextConstructionCSet.map(K, texty));
		assertEquals(textz.map(G), FJCLTextConstructionCSet.map(G, textz));
		assertEquals(textz.map(K), FJCLTextConstructionCSet.map(K, textz));
		assertEquals(x.map(H), FJCLTextConstructionCSet.map(H, x));
	}

	@Test
	public final void xMapFShouldEqualFirstClassMapFX() {
		F<F<CLSentenceOrStatementOrText,FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>, F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>>> map1 = FJCLTextConstructionCSet.map_();
		F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> mapG = map1.f(G);
		assertEquals(textx.map(G), mapG.f(textx));
		assertEquals(texty.map(G), mapG.f(texty));
		assertEquals(textz.map(G), mapG.f(textz));
	}

	
	// Definition: x.bind(G) = join(x.map(G))
	@Test
	public final void bindShouldEqualJoinMap() {
		assertEquals(textx.bind(G), FJCLTextConstructionCSet.join(textx.map(G)));
		assertEquals(textx.bind(K), FJCLTextConstructionCSet.join(textx.map(K)));
		assertEquals(textx.bind(FJCLTextConstructionCSet::unit), FJCLTextConstructionCSet.join(textx.map(FJCLTextConstructionCSet::unit)));
		assertEquals(texty.bind(G), FJCLTextConstructionCSet.join(texty.map(G)));
	}

	@Test
	public final void xBindFShouldEqualStaticBindFX() {
		assertEquals(textx.bind(G), FJCLTextConstructionCSet.bind(G, textx));
		assertEquals(textx.bind(K), FJCLTextConstructionCSet.bind(K, textx));
		assertEquals(textx.bind(FJCLTextConstructionCSet::unit), FJCLTextConstructionCSet.bind(FJCLTextConstructionCSet::unit, textx));
		assertEquals(texty.bind(G), FJCLTextConstructionCSet.bind(G, texty));
		assertEquals(texty.bind(K), FJCLTextConstructionCSet.bind(K, texty));
		assertEquals(texty.bind(FJCLTextConstructionCSet::unit), FJCLTextConstructionCSet.bind(FJCLTextConstructionCSet::unit, texty));
		assertEquals(textz.bind(G), FJCLTextConstructionCSet.bind(G, textz));
		assertEquals(textz.bind(K), FJCLTextConstructionCSet.bind(K, textz));
		assertEquals(textz.bind(FJCLTextConstructionCSet::unit), FJCLTextConstructionCSet.bind(FJCLTextConstructionCSet::unit, textz));
		assertEquals(x.bind(H), FJCLTextConstructionCSet.bind(H, x));
		assertEquals(x.bind(FJCLTextConstructionCSet::unit), FJCLTextConstructionCSet.bind(FJCLTextConstructionCSet::unit, x));
	}

	@Test
	public final void xBindFShouldEqualFirstClassBindFX() {
		F<F<CLSentenceOrStatementOrText,FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>, F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> bind1 = FJCLTextConstructionCSet.bind_();
		F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> bindG = bind1.f(G);
		assertEquals(textx.bind(G), bindG.f(textx));
		assertEquals(texty.bind(G), bindG.f(texty));
		assertEquals(textz.bind(G), bindG.f(textz));
	}

	@Test
	public final void bindUnitShouldEqualIdentity() {
		assertEquals(textx.bind(FJCLTextConstructionCSet::unit), textx);
		assertEquals(texty.bind(FJCLTextConstructionCSet::unit), texty);
		assertEquals(textz.bind(FJCLTextConstructionCSet::unit), textz);
		assertEquals(x.bind(FJCLTextConstructionCSet::unit), x);		
	}
	
	@Test
	public final void bindUnitFShouldEqualMapF() {
		assertEquals(textx.bind(FJCLTextConstructionCSet::unit), textx);
		assertEquals(textx.bind(s -> FJCLTextConstructionCSet.unit(G.f(s))), textx.map(G));
		assertEquals(textx.bind(s -> FJCLTextConstructionCSet.unit(K.f(s))), textx.map(K));
		assertEquals(texty.bind(s -> FJCLTextConstructionCSet.unit(G.f(s))), texty.map(G));
		assertEquals(texty.bind(s -> FJCLTextConstructionCSet.unit(K.f(s))), texty.map(K));
		assertEquals(x.bind(s -> FJCLTextConstructionCSet.unit(H.f(s))), x.map(H));		
	}
	
	// Functor Law 
	// Functor Identity
	@Test
	public final void mapShouldPreserveIdentity() {
		assertEquals(textx.map(s -> s), textx);
		assertEquals(texty.map(s -> s), texty);
		assertEquals(x.map(s -> s), x);
	}


	// Functor Associativity Law
	@Test
	public final void mapShouldPreserveComposition() {
		assertEquals(textx.map(G).map(H), textx.map(K));
		assertEquals(texty.map(G).map(H), texty.map(K));
	}
	
	// Natural Transformation (Unit)
	@Test
	public final void unitFShouldEqualUnitMapF() {
		assertEquals(FJCLTextConstructionCSet.unit(comments, G.f(kex)), FJCLTextConstructionCSet.unit(comments, kex).map(G));
		assertEquals(FJCLTextConstructionCSet.unit(comments, K.f(kex)), FJCLTextConstructionCSet.unit(comments, kex).map(K));
		assertEquals(FJCLTextConstructionCSet.unit(comments, H.f(textx)), FJCLTextConstructionCSet.unit(comments, textx).map(H));
		assertEquals(FJCLTextConstructionCSet.unit(comments, H.f(texty)), FJCLTextConstructionCSet.unit(comments, texty).map(H));
		assertEquals(FJCLTextConstructionCSet.unit(comments, kex), FJCLTextConstructionCSet.unit(comments, kex).map(s -> s));

		assertEquals(FJCLTextConstructionCSet.unit(G.f(kex)), FJCLTextConstructionCSet.unit(kex).map(G));
	
	}

	// Monad Right Identity Law
	
	//join . fmap join     ≡ join . join
	@Test
	public final void joinMapJoinShouldEqualJoinJoin() {
		FJCLTextConstructionCSet<FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> ux = FJCLTextConstructionCSet.unit(x);
		FJCLTextConstructionCSet<FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> uy = FJCLTextConstructionCSet.unit(FJCLTextConstructionCSet.unit(texty));
		FJCLTextConstructionCSet<FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> uz = FJCLTextConstructionCSet.unit(FJCLTextConstructionCSet.unit(textz));
		FJCLTextConstructionCSet<FJCLTextConstructionCSet<FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>>> uxx = FJCLTextConstructionCSet.unit(FJCLTextConstructionCSet.unit(x));
		assertEquals( FJCLTextConstructionCSet.join(ux.map(FJCLTextConstructionCSet.join_())),
			      FJCLTextConstructionCSet.join(FJCLTextConstructionCSet.join(ux)));
		assertEquals( FJCLTextConstructionCSet.join(uy.map(FJCLTextConstructionCSet.join_())),
			      FJCLTextConstructionCSet.join(FJCLTextConstructionCSet.join(uy)));
		assertEquals( FJCLTextConstructionCSet.join(uz.map(FJCLTextConstructionCSet.join_())),
			      FJCLTextConstructionCSet.join(FJCLTextConstructionCSet.join(uz)));
		assertEquals( FJCLTextConstructionCSet.join(uxx.map(FJCLTextConstructionCSet.join_())),
			      FJCLTextConstructionCSet.join(FJCLTextConstructionCSet.join(uxx)));
	}

	// Monad Left Identity Law
	// join . fmap return   ≡ id 
	@Test
	public final void joinMapUnitShouldBeIdentity() {
		assertEquals(FJCLTextConstructionCSet.join(textx.map(FJCLTextConstructionCSet.unit_(comments))),
		               textx);
		assertEquals(FJCLTextConstructionCSet.join(texty.map(FJCLTextConstructionCSet.unit_(comments))),
                texty);
		assertEquals(FJCLTextConstructionCSet.join(textz.map(FJCLTextConstructionCSet.unit_(comments))),
                textz);
		assertEquals(FJCLTextConstructionCSet.join(x.map(FJCLTextConstructionCSet.unit_(comments))),
	                   x);
	}

	// join . return = id
	@Test
	public final void joinUnitShouldBeIdentity() {
		assertEquals(FJCLTextConstructionCSet.join(FJCLTextConstructionCSet.unit(comments,textx)), textx);
		assertEquals(FJCLTextConstructionCSet.join(FJCLTextConstructionCSet.unit(comments, texty)), texty);
		assertEquals(FJCLTextConstructionCSet.join(FJCLTextConstructionCSet.unit(comments, textz)), textz);
		assertEquals(FJCLTextConstructionCSet.join(FJCLTextConstructionCSet.unit(comments, x)), x);
		
	}

	//join . fmap (fmap f) ≡ fmap f . join
	@Test
	public final void joinMapMapFShouldBeMapFJoin() {
		F<    F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>,
		      F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>>  > map1 = FJCLTextConstructionCSet.map_();
		F<    F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>>,
	      F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>>>  > map2 = FJCLTextConstructionCSet.map_();
		F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> mapG = map1.f(G);
		F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>>> mapK = map2.f(K);
		FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> y = FJCLTextConstructionCSet.unit(texty);
		FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> z = FJCLTextConstructionCSet.unit(textz);
		assertEquals( FJCLTextConstructionCSet.join(x.map(mapG)),
				  FJCLTextConstructionCSet.join(x).map(G));
		assertEquals(FJCLTextConstructionCSet.join(y.map(mapG)),
				  FJCLTextConstructionCSet.join(y).map(G));
		assertEquals(FJCLTextConstructionCSet.join(z.map(mapG)),
				  FJCLTextConstructionCSet.join(z).map(G));
		assertEquals( FJCLTextConstructionCSet.join(x.map(mapK)),
				  FJCLTextConstructionCSet.join(x).map(K));
		assertEquals(FJCLTextConstructionCSet.join(y.map(mapK)),
				  FJCLTextConstructionCSet.join(y).map(K));
		assertEquals(FJCLTextConstructionCSet.join(z.map(mapK)),
				  FJCLTextConstructionCSet.join(z).map(K));
		
		assertEquals( x.bind(mapG),
				  FJCLTextConstructionCSet.join(x).map(G));
		assertEquals( y.bind(mapG),
				  FJCLTextConstructionCSet.join(y).map(G));

	}



	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[][] {
				{ AllCLCSetTests.expression0, AllCLCSetTests.contents0, AllCLCSetTests.expressions0,
					AllCLCSetTests.singleton0, AllCLCSetTests.text0,
					AllCLCSetTests.G0, AllCLCSetTests.H0, AllCLCSetTests.comments0 },
					{ AllCLCSetTests.expression1, AllCLCSetTests.contents1, AllCLCSetTests.expressions1,
						AllCLCSetTests.singleton1, AllCLCSetTests.text1,
						AllCLCSetTests.G1, AllCLCSetTests.H1, AllCLCSetTests.comments1 },
					{ AllCLCSetTests.expression2, AllCLCSetTests.contents2, AllCLCSetTests.expressions2,
						AllCLCSetTests.singleton2, AllCLCSetTests.text2,
						AllCLCSetTests.G2, AllCLCSetTests.H2, AllCLCSetTests.comments2 }
					});
	}

}
