package api4kbj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		return language().name() + "." + dialect().name() + "." + name;
	}

}
