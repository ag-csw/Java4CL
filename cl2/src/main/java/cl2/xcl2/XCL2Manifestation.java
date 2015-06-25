package cl2.xcl2;

import cl2.CL;
import cl2.CLDialectType;
import cl2a.CLManifestation;
import api4kbj.DialectTypeEnvironment;

public class XCL2Manifestation extends CLManifestation {

	public XCL2Manifestation(CLDialectType<?> wrappedDialectType, Object wrappedValue,
			DialectTypeEnvironment environment) {
		super(CL.XCL2, wrappedDialectType, wrappedValue, environment);
	}

}
