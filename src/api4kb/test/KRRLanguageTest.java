import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.runner.RunWith;

import api4kbj.AbstractKRRLanguage;
import api4kbj.KRRLanguage;
import api4kbj.KRRLogic;

@RunWith(Parameterized.class)
public class KRRLanguageTest {
	public KRRLanguage language;

	public KRRLanguageTest(KRRLanguage language) {
		this.language = language;
	}

	@Test
	public final void nameOfLanguageShouldNotBeEmpty() {
		assertTrue(language.name().length() > 0);
	}

	@Test
	public final void nameOfLogicOfLanguageShouldNotBeEmpty() {
		assertTrue(language.logic().name().length() > 0);
	}

	@Test
	public final void equalsShouldBeBasedOnFields() {
		KRRLanguage otherLanguage = new AbstractKRRLanguage(language.name(),
				language.logic()) {
		};
		assertEquals(otherLanguage, language);
	}

	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[] { new AbstractKRRLanguage(
				"Language One", new KRRLogic() {

					@Override
					public String name() {
						return "Logic A";
					}
				}) {
		} }, new Object[] { new AbstractKRRLanguage("Language Two",
				new KRRLogic() {

					@Override
					public String name() {
						return "Logic B";
					}
				}) {
		} }

		);
	}
}
