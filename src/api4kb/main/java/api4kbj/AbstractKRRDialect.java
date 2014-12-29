package api4kbj;

public abstract class AbstractKRRDialect implements KRRDialect {

	private String name;
	private AbstractKRRLanguage lang;
	private AbstractKRRDialectType<?> defaultDialectType;

	public AbstractKRRDialect(String name, AbstractKRRLanguage lang) {
		this.name = name;
		this.lang = lang;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public AbstractKRRLanguage language() {
		return lang;
	}

	@Override
	public AbstractKRRDialectType<?> defaultDialectType() {
		return defaultDialectType;
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
		result = prime
				* result
				+ ((defaultDialectType == null) ? 0 : defaultDialectType
						.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof AbstractKRRDialect)) {
			return false;
		}
		AbstractKRRDialect other = (AbstractKRRDialect) obj;
		if (defaultDialectType == null) {
			if (other.defaultDialectType != null) {
				return false;
			}
		} else if (!defaultDialectType.equals(other.defaultDialectType)) {
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
		return true;
	}

}
