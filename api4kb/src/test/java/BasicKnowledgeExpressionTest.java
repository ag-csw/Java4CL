import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.runner.RunWith;

import functional.Option;
import api4kbc.API4KB;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeSourceLevel;

@RunWith(Parameterized.class)
public class BasicKnowledgeExpressionTest {
	
	public BasicKnowledgeExpressionTest(TestKE expression,
			KRRLanguage language, String symbol, Option<? extends TestKE> arg) {
		super();
		this.expression = expression;
		this.language = language;
		this.symbol = symbol;
		this.arg = arg;
	}

	public TestKE expression;
	public KRRLanguage language;
	public String symbol;
	public Option<? extends TestKE> arg;

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
	public void expressionArgumentShouldBeAsConstructed() {
		assertEquals(expression.arg(),arg);
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


		return Arrays.asList(new Object[][] { { AllTests.expression0, AllTests.lang0, AllTests.str0, AllTests.arg0 },
				{ AllTests.expression1, AllTests.lang1, AllTests.str1, AllTests.arg1 }, { AllTests.expression2, AllTests.lang2, AllTests.str2, AllTests.arg2 } });
	}
}




