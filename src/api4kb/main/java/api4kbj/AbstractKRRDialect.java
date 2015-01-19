package api4kbj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKRRDialect implements KRRDialect {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKRRDialect(String name, KRRLanguage lang) {
		this.name = name;
		this.lang = lang;
	}

	private final String name;
	private final KRRLanguage lang;

	@Override
	public String name() {
		return name;
	}

	@Override
	public KRRLanguage language() {
		return lang;
	}

	@Override
	public String toString() {
		return language().toString() + "." + name();
	}

}
