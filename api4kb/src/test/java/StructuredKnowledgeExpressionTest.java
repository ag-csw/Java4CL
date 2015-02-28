import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fj.Equal;
import fj.Ord;
import fj.data.Set;
import api4kba.AbstractKRRLanguage;
import api4kbc.API4KB;
import api4kbc.FJSetKnowledgeExpression;
import api4kbc.FJSetStructuredKnowledgeExpression;
import api4kbj.KRRLanguage;
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
		System.out.println("Languages Expected");
		for(KRRLanguage lang: languages){
			  System.out.println(lang.name());	
			}
		System.out.println("Languages Found");
		Set<KRRLanguage> exlanguages  = expression.languages();
		for(KRRLanguage lang: exlanguages){
			  System.out.println(lang.name());	
			}

		Ord<KRRLanguage> ordexlang  = exlanguages.ord();
		Ord<KRRLanguage> ordlang  = languages.ord();
		assertEquals(ordexlang, ordlang);
		Equal<KRRLanguage> langEqual = Equal.anyEqual();
		Equal<Set<KRRLanguage>> setEqual = Equal.setEqual(langEqual);
		assertTrue(exlanguages.equals(languages));
		//assertTrue(exlanguages.union(languages).minus(exlanguages.intersect(languages)).isEmpty());
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
        


		return Arrays.asList(new Object[][] { 
				{ AllFJSetTests.stexpr0, AllFJSetTests.languages0, AllFJSetTests.expressions0 },
				{ AllFJSetTests.stexpr1, AllFJSetTests.languages1, AllFJSetTests.expressions1 },
				{ AllFJSetTests.stexpr2, AllFJSetTests.languages2, AllFJSetTests.expressions2 },
				{ AllFJSetTests.stexpr3, AllFJSetTests.languages2, AllFJSetTests.stexpr3.components() },
				{ AllFJSetTests.stexpr4, AllFJSetTests.languages2, AllFJSetTests.stexpr3.components() }				
			});
	}


}
