package api4kba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KRRDialect;
import api4kbj.KRRFormat;

public class AbstractKRRFormat implements KRRFormat {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKRRFormat(String name, KRRDialect dialect) {
		this.name = name;
		this.dialect = dialect;
	}

	private String name;
	private KRRDialect dialect;

	@Override
	public String name() {
		return name;
	}

	@Override
	public KRRDialect dialect() {
		return dialect;
	}

	@Override
	public String toString() {
		return dialect().toString() + "." + name();
	}

}
