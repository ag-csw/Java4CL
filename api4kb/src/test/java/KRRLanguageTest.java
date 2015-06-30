import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.runner.RunWith;

import api4kba.AbstractKRRLanguage;
import api4kba.AbstractKRRLogic;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpressionLike;

@RunWith(Parameterized.class)
public class KRRLanguageTest {

	public KRRLanguageTest(KRRLanguage language, String name,
			Class<? extends KnowledgeExpressionLike> clazz, AbstractKRRLogic logic) {
		super();
		this.language = language;
		this.name = name;
		this.clazz = clazz;
		this.logic = logic;
	}


	public KRRLanguage language;
	public String name;
	public Class<? extends KnowledgeExpressionLike> clazz;
	public AbstractKRRLogic logic;

	@Test
	public final void languageShouldBeEqualToLanguageBuiltFromStaticFactoryMethod() {
        KRRLanguage otherLanguage = AbstractKRRLanguage.
        		language(language.name(), language.asClass(), language.logic());		
		assertEquals(language, otherLanguage);
	}

	@Test
	public void languageNameShouldBeAsConstructed() {
		assertEquals(language.name(), name);
	}

	@Test
	public void languageClassShouldBeAsConstructed() {
		assertEquals(language.asClass(), clazz);
	}

	@Test
	public void languageLogicShouldBeAsConstructed() {
		assertEquals(language.logic(), logic);
	}


	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[][] { 
				{ AllTests.lang0, AllTests.languageName0, AllTests.clazz0, AllTests.logicA },
				{ AllTests.lang1, AllTests.languageName1, AllTests.clazz1, AllTests.logicA },
				{ AllTests.lang2, AllTests.languageName2, AllTests.clazz2, AllTests.logicB },
		});
	}

}
