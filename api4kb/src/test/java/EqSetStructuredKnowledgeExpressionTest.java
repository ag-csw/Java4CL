import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import functional.EqSet;
import api4kbc.API4KB;
import api4kbc.EqSetKnowledgeExpression;
import api4kbc.EqSetStructuredKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeSourceLevel;

@RunWith(Parameterized.class)
public class EqSetStructuredKnowledgeExpressionTest {

	public EqSetStructuredKnowledgeExpressionTest(
			EqSetStructuredKnowledgeExpression expression,
			EqSet<KRRLanguage> languages, EqSet<EqSetKnowledgeExpression> components) {
		super();
		this.expression = expression;
		this.languages = languages;
		this.components = components;
	}
	
	private final EqSetStructuredKnowledgeExpression expression;
	private final EqSet<KRRLanguage> languages;
	private final EqSet<EqSetKnowledgeExpression> components;	

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
		System.out.println("Languages Expected");
		for(KRRLanguage lang: languages){
			  System.out.println(lang.name());	
			}
		System.out.println("Languages Found");
		EqSet<KRRLanguage> exlanguages  = expression.languages();
		for(KRRLanguage lang: exlanguages){
			  System.out.println(lang.name());	
			}

		assertEquals(expression.languages(), languages);
	}
	
	


	@Test
	public void expressionComponentsShouldBeAsConstructed() {
		EqSet<EqSetKnowledgeExpression> excomponents  = expression.components();
		assertEquals(excomponents, components);
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
        


		return Arrays.asList(new Object[][] { 
				{ AllEqSetTests.stexpr0, AllEqSetTests.languages0, AllEqSetTests.expressions0 },
				{ AllEqSetTests.stexpr1, AllEqSetTests.languages1, AllEqSetTests.expressions1 },
				{ AllEqSetTests.stexpr2, AllEqSetTests.languages2, AllEqSetTests.expressions2 },
				{ AllEqSetTests.stexpr3, AllEqSetTests.languages2, AllEqSetTests.stexpr3.components() },
				{ AllEqSetTests.stexpr4, AllEqSetTests.languages2, AllEqSetTests.stexpr3.components() }				
			});
	}


}
