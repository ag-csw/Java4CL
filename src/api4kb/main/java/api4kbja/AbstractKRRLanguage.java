package api4kbja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;
import api4kbj7.IKRRLogic;

public abstract class AbstractKRRLanguage implements KRRLanguage {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKRRLanguage(String name, IKRRLogic logic) {
		this.name = name;
		this.logic = logic;
	}

	private final String name;

	private Class<? extends KnowledgeExpression> clazz;

	private final IKRRLogic logic;

	@Override
	public String name() {
		return name;
	}

	@Override
	public Class<? extends KnowledgeExpression> asClass() {
		return clazz;
	}

	@Override
	public IKRRLogic logic() {
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
		result = prime * result + ((logic == null) ? 0 : logic.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractKRRLanguage)) {
			return false;
		}
		AbstractKRRLanguage other = (AbstractKRRLanguage) obj;
		if (logic == null) {
			if (other.logic != null) {
				return false;
			}
		} else if (!logic.equals(other.logic)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	// TODO make this package protected?
	@Override
	public void setClass(Class<? extends KnowledgeExpression> clazz) {
		this.clazz = clazz;

	}

}
