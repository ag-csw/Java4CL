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
        


		return Arrays.asList(new Object[][] { { AllFJSetTests.stexpr0, AllFJSetTests.languages0, AllFJSetTests.expressions0 },
				{ AllFJSetTests.stexpr1, AllFJSetTests.languages1, AllFJSetTests.expressions1 } });
	}


}
