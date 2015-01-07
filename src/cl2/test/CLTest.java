import static org.junit.Assert.*;
import graphenvironment.FocusedGraphImmutableEnvironment;

import org.junit.Test;

import cl2.CL;


public class CLTest {

	@Test
	public void testLang() {
		assertEquals("Name of the static Common Logic language is not correctly set.", "Common Logic", CL.LANG.name());
	}
	
	public void testNamespace() {
		assertEquals("The Common Logic URI is not the URI of the Flyweight Namespace for use in XCL2 dom4j instances.", CL.URI_XCL2, CL.NS_XCL2.getURI());
	}

	@Test
	public void testEnv() {
		assertTrue("The Common Logic default (singleton) environment does not contain the CL language.", CL.CL_DEFAULT_ENVIRONMENT.containsLanguage(CL.LANG));
		assertEquals("The Common Logic language is not the focus language of the default CL environment.", CL.LANG, CL.CL_DEFAULT_ENVIRONMENT.focusLanguage().value());
		assertEquals("Equality of environments is not based on fields", CL.CL_DEFAULT_ENVIRONMENT, new FocusedGraphImmutableEnvironment(
				CL.LANG));

	}
}
