package api4kba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KRRLanguage;
import api4kbj.KRRLogic;
import api4kbj.KnowledgeExpression;

public abstract class AbstractKRRLanguage implements KRRLanguage {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKRRLanguage(String name, KRRLogic logic, Class<? extends KnowledgeExpression> clazz) {
		this.name = name;
		this.logic = logic;
		this.clazz = clazz;
	}

	private final String name;

	private final Class<? extends KnowledgeExpression> clazz;

	private final KRRLogic logic;

	@Override
	public String name() {
		return name;
	}

	@Override
	public Class<? extends KnowledgeExpression> asClass() {
		return clazz;
	}

	@Override
	public KRRLogic logic() {
		return logic;
	}

	@Override
	public String toString() {
		return name() + "." + asClass().getName() + ":" + logic.name();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
		result = prime * result + ((logic == null) ? 0 : logic.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractKRRLanguage other = (AbstractKRRLanguage) obj;
		if (clazz == null) {
			if (other.clazz != null)
				return false;
		} else if (!clazz.equals(other.clazz))
			return false;
		if (logic == null) {
			if (other.logic != null)
				return false;
		} else if (!logic.equals(other.logic))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


}
