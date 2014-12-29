package api4kbj;

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
	private AbstractKRRDialect defaultDialect;
	private AbstractKRRDialectType<?> defaultDialectType;
	protected ImmutableEnvironment defaultEnvironment;

	@Override
	public String name() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	// TODO
	// public KRRLogic logic(){
	// return logic;
	// }
	@Override
	public AbstractKRRDialect defaultDialect() {
		return defaultDialect;
	}

	@Override
	public AbstractKRRDialectType<?> defaultDialectType() {
		return defaultDialectType;
	}

	@Override
	public CodecSystem<?, ?> defaultSystem() {
		return defaultDialectType().defaultSystem();
	}

	@Override
	public ImmutableEnvironment defaultEnvironment() {
		return defaultEnvironment;
	}

}
