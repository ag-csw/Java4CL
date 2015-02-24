import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fj.Ord;
import fj.data.Set;
import api4kba.AbstractKRRLanguage;
import api4kbc.API4KB;
import api4kbc.FJSetKnowledgeExpression;
import api4kbc.FJSetStructuredKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KRRLogic;
import api4kbj.KnowledgeSourceLevel;

@RunWith(Parameterized.class)
public class StructuredKnowledgeExpressionTest {

	public StructuredKnowledgeExpressionTest(
			FJSetStructuredKnowledgeExpression expression,
			Set<KRRLanguage> languages, Set<FJSetKnowledgeExpression> components) {
		super();
		this.expression = expression;
		this.languages = languages;
		this.components = components;
	}

	private final FJSetStructuredKnowledgeExpression expression;
	private final Set<KRRLanguage> languages;
	private final Set<FJSetKnowledgeExpression> components;	

	@Test
	public void expressionLevelShouldBeExpression() {
		assertEquals(expression.level(), KnowledgeSourceLevel.EXPRESSION);
	}

	@Test
	public void expressionShouldBeBasic() {
		assertFalse(expression.isBasic());
	}

	@Test
	public void expressionLanguagesShouldBeAsConstructed() {
		Set<KRRLanguage> exlanguages  = expression.languages();
		Ord<KRRLanguage> ordlang  = exlanguages.ord();
		Ord<Set<KRRLanguage>> ordlangs = Ord.setOrd(ordlang);
		assertTrue(ordlangs.eq(exlanguages, languages));
	}

	@Test
	public void expressionComponentsShouldBeAsConstructed() {
		Set<FJSetKnowledgeExpression> excomponents  = expression.components();
		Ord<FJSetKnowledgeExpression> ordcomp  = excomponents.ord();
		Ord<Set<FJSetKnowledgeExpression>> ordcomps = Ord.setOrd(ordcomp);
		assertTrue(ordcomps.eq(excomponents, components));
	}

	@Test
	public void expressionAndItsLanguageShouldSatisfyUsesLanguageRelation() {
		for (KRRLanguage lang : expression.languages()) {
			assertTrue(API4KB.usesLanguage(expression, lang));
			assertTrue(expression.usesLanguage(lang));
		}
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		KRRLogic logicA = new KRRLogic() {

			@Override
			public String name() {
				return "Logic A";
			}
		};
		class LangA extends AbstractKRRLanguage {

			public LangA(String name) {
				super(name, logicA);
			}

		}

		KRRLogic logicB = new KRRLogic() {

			@Override
			public String name() {
				return "Logic B";
			}
		};
		class LangB extends AbstractKRRLanguage {

			public LangB(String name) {
				super(name, logicB);
			}

		}
		
		KRRLanguage lang0 = new LangA("Language Zero");
		class TestKE0 extends TestFJSetKE {

			TestKE0(String value) {
				super(value.toLowerCase(), lang0);
			}


		}
		lang0.setClass(TestKE0.class);


		KRRLanguage lang1 = new LangA("Language One");
		class TestKE1 extends TestFJSetKE {

			TestKE1(String value) {
				super(value, lang1);
			}


		}
		lang1.setClass(TestKE1.class);

		KRRLanguage lang2 = new LangB("Language Two");
		class TestKE2 extends TestFJSetKE {

			TestKE2(String value) {
				super(value.toUpperCase(), lang2);
			}


		}
		lang2.setClass(TestKE2.class);
		


        String str = "Hello World!";
        TestKE0 expression0 = new TestKE0(str);
        TestKE1 expression1 = new TestKE1(str);
        TestKE2 expression2 = new TestKE2(str);

        String str0 = "hello world!";
        String str1 = str;
        String str2 = "HELLO WORLD!";
        
        
        
        Ord<KRRLanguage> langOrder = Ord.hashEqualsOrd();
		Ord<FJSetKnowledgeExpression> exprOrder  = Ord.hashEqualsOrd();

		Set<KRRLanguage> languages0 = Set.set(langOrder , lang0, lang1, lang2);
		Set<FJSetKnowledgeExpression> expressions0 = Set.set(exprOrder, expression0, expression1, expression2);
		FJSetStructuredKnowledgeExpression stexpr0 = new FJSetStructuredKnowledgeExpression(languages0 , expressions0);

		Set<KRRLanguage> languages1 = Set.set(langOrder , lang1, lang2);
		Set<FJSetKnowledgeExpression> expressions1 = Set.set(exprOrder, expression1, expression2);
		FJSetStructuredKnowledgeExpression stexpr1 = new FJSetStructuredKnowledgeExpression(expressions1);


		return Arrays.asList(new Object[][] { { stexpr0, languages0, expressions0 },
				{ stexpr1, languages1, expressions1 } });
	}


}
