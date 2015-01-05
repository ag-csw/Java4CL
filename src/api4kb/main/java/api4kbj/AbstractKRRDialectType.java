package api4kbj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKRRDialectType<T> implements KRRDialectType<T> {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKRRDialectType(String name, KRRDialect dialect, Class<T> type) {
		this.name = name;
		this.dialect = dialect;
		this.type = type;
	}

	private final String name;
	private final KRRDialect dialect;
	private final Class<T> type;

	@Override
	public String name() {
		return name;
	}

	@Override
	public KRRDialect dialect() {
		return dialect;
	}

	@Override
	public Class<T> type() {
		return type;
	}

	@Override
	public String toString() {
		return language().name() + "." + dialect().name() + "." + type() + "."
				+ name();
	}

}
