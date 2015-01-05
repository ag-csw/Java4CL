package api4kbj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKRRFormatType<S> implements KRRFormatType<S> {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKRRFormatType(String name, KRRFormat format, Class<S> type) {
		this.name = name;
		this.format = format;
		this.type = type;
	}

	private final String name;
	private final KRRFormat format;
	private final Class<S> type;

	@Override
	public String name() {
		return name;
	}

	@Override
	public KRRFormat format() {
		return format;
	}

	@Override
	public Class<S> type() {
		return type;
	}

	@Override
	public String toString() {
		return language().name() + "." + dialect().name() + "."
				+ format().name() + "." + name;
	}

	@Override
	public KRRLanguage language() {
		return dialect().language();
	}

}
