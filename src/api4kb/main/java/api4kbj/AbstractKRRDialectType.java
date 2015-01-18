package api4kbj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKRRDialectType<T> implements KRRDialectType<T> {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKRRDialectType(String name, KRRDialect dialect, Class<T> clazz) {
		this.name = name;
		this.dialect = dialect;
		this.clazz = clazz;
	}

	private final String name;
	private final KRRDialect dialect;
	private final Class<T> clazz;

	@Override
	public String name() {
		return name;
	}

	@Override
	public KRRDialect dialect() {
		return dialect;
	}

	@Override
	public Class<T> asClass() {
		return clazz;
	}

	@Override
	public String toString() {
		return language().name() + "." + dialect().name() + "." + asClass() + "."
				+ name();
	}

}
