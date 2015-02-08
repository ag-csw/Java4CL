package api4kbja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;

public abstract class AbstractKRRFormatType<S> implements KRRFormatType<S> {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKRRFormatType(String name, KRRFormat format,
			Class<? extends S> clazz) {
		this.name = name;
		this.format = format;
		this.clazz = clazz;
	}

	private final String name;
	private final KRRFormat format;
	private final Class<? extends S> clazz;

	@Override
	public String name() {
		return name;
	}

	@Override
	public KRRFormat format() {
		return format;
	}

	@Override
	public Class<? extends S> asClass() {
		return clazz;
	}

	@Override
	public String toString() {
		return format().toString() + "." + asClass().getName() + "." + name();
	}

}
