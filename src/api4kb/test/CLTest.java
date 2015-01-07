import static org.junit.Assert.*;

import org.junit.Test;

import cl2.CL;


public class CLTest {

	@Test
	public void testLang() {
		assert CL.LANG.name().equals("Common Logic") : "Failed set of name for CL.LANG.";
	}

	@Test
	public void testEnc() {
		
	}
}
