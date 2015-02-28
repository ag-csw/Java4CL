import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.runner.RunWith;

import api4kba.AbstractKRRLogic;

@RunWith(Parameterized.class)
public class KRRLogicTest {
	public KRRLogicTest(AbstractKRRLogic logic, String name) {
		super();
		this.logic = logic;
		this.name = name;
	}

	public AbstractKRRLogic logic;
	public String name;

	@Test
	public final void logicShouldBeEqualToLogicBuiltFromStaticFactoryMethod() {
        AbstractKRRLogic otherLogic = AbstractKRRLogic.logic(logic.name());		
		assertEquals(logic, otherLogic);
	}
	
	@Test
	public void logicNameShouldBeAsConstructed() {
		assertEquals(logic.name(), name);
	}



	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(new Object[][] { 
				{ AllTests.logicA, AllTests.logicNameA },
				{ AllTests.logicB, AllTests.logicNameB }
		});
	}

}
