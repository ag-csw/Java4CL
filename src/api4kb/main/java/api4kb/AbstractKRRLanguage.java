package api4kb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKRRLanguage implements KRRLanguage {

	public AbstractKRRLanguage(String name) {
		this.name = name;
		// this.logic = logic;
	}

	private final String name;
	// private final Logic logic;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return name;
	}
	// TODO
	//public KRRLogic getLogic(){
	//	return logic;
	//}
			
}
