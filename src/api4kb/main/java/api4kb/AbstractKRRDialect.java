package api4kb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKRRDialect<T> implements KRRDialect<T> {

	public AbstractKRRDialect(String name, KRRLanguage lang) {
		this.name = name;
		this.lang = lang;
	}

	private final String name;
	private final KRRLanguage lang;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public String getName(){
		return name;
	}
	public KRRLanguage getLanguage(){
		return lang;
	}
	public String toString(){
		return lang.getName() + "." + name;
	}


}
