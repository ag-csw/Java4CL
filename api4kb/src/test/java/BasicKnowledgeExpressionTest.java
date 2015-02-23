import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.runner.RunWith;

import fj.F;
import api4kba.AbstractBasicKnowledgeExpression;
import api4kba.AbstractKRRLanguage;
import api4kbc.API4KB;
import api4kbc.FLanguageMapping;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KRRLogic;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;

@RunWith(Parameterized.class)
public class BasicKnowledgeExpressionTest {
	public BasicKnowledgeExpressionTest(TestKE expression,
			KRRLanguage language, String symbol) {
		super();
		this.expression = expression;
		this.language = language;
		this.symbol = symbol;
	}

	public TestKE expression;
	public KRRLanguage language;
	public String symbol;

	@Test
	public void expressionShouldBeBasic() {
		assertTrue(expression.isBasic());
	}

	@Test
	public void expressionDialectShouldBeAsConstructed() {
		assertEquals(expression.language(), language);
	}

	@Test
	public void expressionAndItsLanguageShouldSatisfyUsesLanguageRelation() {
		assertTrue(API4KB.usesLanguage(expression, expression.language()));
		assertTrue(expression.usesLanguage(expression.language()));
	}

	@Test
	public void expressionValueShouldBeGivenString() {
		assertEquals(expression.symbol(), symbol);
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
		class TestKE0 extends TestKE {

			TestKE0(String value) {
				super(value.toLowerCase(), lang0);
			}


		}
		lang0.setClass(TestKE0.class);


		KRRLanguage lang1 = new LangA("Language One");
		class TestKE1 extends TestKE {

			TestKE1(String value) {
				super(value, lang1);
			}


		}
		lang1.setClass(TestKE1.class);

		KRRLanguage lang2 = new LangB("Language Two");
		class TestKE2 extends TestKE {

			TestKE2(String value) {
				super(value.toUpperCase(), lang2);
			}


		}
		lang2.setClass(TestKE2.class);
		


        String str = "Hello World!";
		BasicKnowledgeExpression expression0 = new TestKE0(str);
		BasicKnowledgeExpression expression1 = new TestKE1(str);
		BasicKnowledgeExpression expression2 = new TestKE2(str);

        String str0 = "hello world!";
        String str1 = str;
        String str2 = "HELLO WORLD!";


		return Arrays.asList(new Object[][] { { expression0, lang0, str0 },
				{ expression1, lang1, str1 }, { expression2, lang2, str2 } });
	}
}
