package api4kba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KRRLogic;

public abstract class AbstractKRRLogic implements KRRLogic {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	private AbstractKRRLogic(final String name) {
		this.name = name;
	}

	private final String name;
	
	public static AbstractKRRLogic logic(final String name){
		return new AbstractKRRLogic(name){};
	}

	public String name() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractKRRLogic))
			return false;
		AbstractKRRLogic other = (AbstractKRRLogic) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
