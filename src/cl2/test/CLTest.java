import static org.junit.Assert.*;
import functional.None;
import graphenvironment.FocusedGraphImmutableLanguageEnvironment;

import org.junit.Test;

import cl2.CL;
import cl2.CLCommentExpression;
import cl2.CLExpression;

public class CLTest {

	@Test
	public void testLang() {
		assertEquals(
				"The name of the Common Logic language should be 'Common Logic'.",
				"Common Logic", CL.LANG.name());
	}

	public void testNamespace() {
		assertEquals(
				"The Common Logic URI should be the URI of the Flyweight Namespace for use in XCL2 dom4j instances.",
				CL.URI_XCL2, CL.NS_XCL2.getURI());
	}

	@Test
	public void testEnv() {
		assertTrue(
				"The Common Logic default (singleton) environment should contain the CL language.",
				CL.CL_DEFAULT_ENVIRONMENT.containsMember(CL.LANG));
		assertEquals(
				"The Common Logic language should be the focus language of the default CL environment.",
				CL.LANG, CL.CL_DEFAULT_ENVIRONMENT.focusMember().value());
		assertEquals("Equality of environments should be based on fields",
				CL.CL_DEFAULT_ENVIRONMENT,
				new FocusedGraphImmutableLanguageEnvironment(CL.LANG));
		assertFalse(
				"null should not be compatible with the Common Logic default environment",
				CL.CL_DEFAULT_ENVIRONMENT.isCompatibleWith(null));
		CLExpression expression = new CLExpression();
		assertEquals("The class of a CLExpression should be CLEXpression", CLExpression.class, expression.getClass());
		assertTrue(
				"A Common Logic expression should be compatible with the Common Logic default environment",
				CL.CL_DEFAULT_ENVIRONMENT
						.isCompatibleWith(expression));

	}
}
