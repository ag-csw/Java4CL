package api4kbj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKRRDialectType<T> implements KRRDialectType<T> {

	public AbstractKRRDialectType(String name, AbstractKRRDialect dialect,
			Class<T> type, AbstractCodecSystem<T, ?> defaultSystem) {
		this.name = name;
		this.dialect = dialect;
		this.lang = dialect.language();
		this.type = type;
		this.defaultSystem = defaultSystem;
	}

	private final String name;
	private final AbstractKRRDialect dialect;
	private final Class<T> type;
	private final AbstractKRRLanguage lang;
	private final AbstractCodecSystem<T, ?> defaultSystem;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public String name() {
		return name;
	}

	@Override
	public AbstractKRRDialect dialect() {
		return dialect;
	}

	@Override
	public AbstractKRRLanguage language() {
		return lang;
	}

	@Override
	public Class<T> type() {
		return type;
	}

	@Override
	public String toString() {
		return lang.name() + "." + name;
	}

	@Override
	public AbstractCodecSystem<T, ?> defaultSystem() {
		return defaultSystem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((defaultSystem == null) ? 0 : defaultSystem.hashCode());
		result = prime * result + ((dialect == null) ? 0 : dialect.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractKRRDialectType)) {
			return false;
		}
		AbstractKRRDialectType other = (AbstractKRRDialectType) obj;
		if (defaultSystem == null) {
			if (other.defaultSystem != null) {
				return false;
			}
		} else if (!defaultSystem.equals(other.defaultSystem)) {
			return false;
		}
		if (dialect == null) {
			if (other.dialect != null) {
				return false;
			}
		} else if (!dialect.equals(other.dialect)) {
			return false;
		}
		if (lang == null) {
			if (other.lang != null) {
				return false;
			}
		} else if (!lang.equals(other.lang)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}

}
