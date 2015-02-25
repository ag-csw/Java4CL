import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.runner.RunWith;

import api4kbc.API4KB;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeSourceLevel;

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
	public void expressionLevelShouldBeExpression() {
		assertEquals(expression.level(), KnowledgeSourceLevel.EXPRESSION);
	}

	@Test
	public void expressionShouldBeBasic() {
		assertTrue(expression.isBasic());
	}

	@Test
	public void expressionLanguageShouldBeAsConstructed() {
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

		BasicKnowledgeExpression expression0 = new TestKE0(AllTests.str);
		BasicKnowledgeExpression expression1 = new TestKE1(AllTests.str);
		BasicKnowledgeExpression expression2 = new TestKE2(AllTests.str);



		return Arrays.asList(new Object[][] { { expression0, AllTests.lang0, AllTests.str0 },
				{ expression1, AllTests.lang1, AllTests.str1 }, { expression2, AllTests.lang2, AllTests.str2 } });
	}
}




