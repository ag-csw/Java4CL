package api4kb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKRRDialectType<T> implements KRRDialectType<T> {

	public AbstractKRRDialectType(String name, AbstractKRRDialect dialect,
			Class<T> type, AbstractCodecSystem<T, ?> defaultSystem) {
		this.name = name;
		this.dialect = dialect;
		this.lang = dialect.getLanguage();
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
	public String getName() {
		return name;
	}

	@Override
	public AbstractKRRDialect getDialect() {
		return dialect;
	}

	@Override
	public AbstractKRRLanguage getLanguage() {
		return lang;
	}

	@Override
	public Class<T> getType() {
		return type;
	}

	@Override
	public String toString() {
		return lang.getName() + "." + name;
	}

	@Override
	public AbstractCodecSystem<T, ?> defaultSystem() {
		return defaultSystem;
	}

}
