package cl2.xcl2;

import api4kbj.DialectTypeEnvironment;
import cl2.CL;
import cl2.CLDialectType;
import cl2a.CLManifestation;

public class XCL2Manifestation extends CLManifestation {

	public XCL2Manifestation(CLDialectType<?> wrappedDialectType, Object wrappedValue,
			DialectTypeEnvironment environment) {
		super(CL.XCL2, wrappedDialectType, wrappedValue, environment);
	}

}
