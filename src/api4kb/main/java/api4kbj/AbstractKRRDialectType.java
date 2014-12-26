package api4kbj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKRRDialectType<T> implements KRRDialectType<T> {

	public AbstractKRRDialectType(String name, AbstractKRRDialect dialect,
			Class<T> type, AbstractCodecSystem<T, ?> defaultSystem) {
		this.name = name;
		this.dialect = dialect;
		this.lang = dialect.language();
		this.type = type;
		this.defaultSystem = defaultSystem;
	}

	private final String name;
	private final AbstractKRRDialect dialect;
	private final Class<T> type;
	private final AbstractKRRLanguage lang;
	private final AbstractCodecSystem<T, ?> defaultSystem;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public String name() {
		return name;
	}

	@Override
	public AbstractKRRDialect dialect() {
		return dialect;
	}

	@Override
	public AbstractKRRLanguage language() {
		return lang;
	}

	@Override
	public Class<T> type() {
		return type;
	}

	@Override
	public String toString() {
		return lang.name() + "." + name;
	}

	@Override
	public AbstractCodecSystem<T, ?> defaultSystem() {
		return defaultSystem;
	}

}
