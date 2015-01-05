package api4kbj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKRRLanguage implements KRRLanguage {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKRRLanguage(String name) {
		this.name = name;
		// this.logic = logic;
	}

	private final String name;

	// private final Logic logic;

	@Override
	public String name() {
		return name;
	}

	// TODO
	// public KRRLogic logic(){
	// return logic;
	// }

	@Override
	public String toString() {
		return name;
	}

}
