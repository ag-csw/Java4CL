package api4kbj;

import graphenvironment.GraphImmutableEnvironment;

public abstract class AbstractKnowledgeResource implements KnowledgeResource {

	public AbstractKnowledgeResource(KRRLanguage lang) {
		this(System.in, System.out, lang.defaultSystem(), lang
				.defaultDialectType(), lang.defaultDialect(), lang
				.defaultEnvironment(), lang);
	}

	public AbstractKnowledgeResource(Object defaultSender,
			Object defaultReceiver, CodecSystem<?, ?> defaultSystem,
			KRRDialectType<?> defaultDialectType, KRRDialect defaultDialect,
			ImmutableEnvironment defaultEnvironment, KRRLanguage defaultLanguage) {
		this.defaultSender = defaultSender;
		this.defaultReceiver = defaultReceiver;
		this.defaultSystem = defaultSystem;
		this.defaultDialectType = defaultDialectType;
		this.defaultDialect = defaultDialect;
		this.defaultEnvironment = defaultEnvironment;
		this.defaultLanguage = defaultLanguage;
	}

	public AbstractKnowledgeResource(GraphImmutableEnvironment env) {
		this(System.in, System.out, env.defaultLanguage().defaultSystem(), env
				.defaultLanguage().defaultDialectType(), env.defaultLanguage()
				.defaultDialect(), env, env.defaultLanguage());
	}

	private Object defaultSender;
	private Object defaultReceiver;
	private CodecSystem<?, ?> defaultSystem;
	private KRRDialectType<?> defaultDialectType;
	private KRRDialect defaultDialect;
	private ImmutableEnvironment defaultEnvironment;
	private KRRLanguage defaultLanguage;

	@Override
	public ImmutableEnvironment defaultEnvironment() {
		return defaultEnvironment;
	}

	@Override
	public KRRLanguage defaultLanguage() {
		return defaultLanguage;
	}

	@Override
	public KRRDialect defaultDialect() {
		return defaultDialect;
	}

	@Override
	public KRRDialectType<?> defaultDialectType() {
		return defaultDialectType;
	}

	@Override
	public CodecSystem<?, ?> defaultCodecSystem() {
		return defaultSystem;
	}

	@Override
	public Object defaultReceiver() {
		return defaultReceiver;
	}

	@Override
	public Object defaultSender() {
		return defaultSender;
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
				+ ((defaultDialect == null) ? 0 : defaultDialect.hashCode());
		result = prime
				* result
				+ ((defaultDialectType == null) ? 0 : defaultDialectType
						.hashCode());
		result = prime
				* result
				+ ((defaultEnvironment == null) ? 0 : defaultEnvironment
						.hashCode());
		result = prime * result
				+ ((defaultLanguage == null) ? 0 : defaultLanguage.hashCode());
		result = prime * result
				+ ((defaultReceiver == null) ? 0 : defaultReceiver.hashCode());
		result = prime * result
				+ ((defaultSender == null) ? 0 : defaultSender.hashCode());
		result = prime * result
				+ ((defaultSystem == null) ? 0 : defaultSystem.hashCode());
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
		if (!(obj instanceof AbstractKnowledgeResource)) {
			return false;
		}
		AbstractKnowledgeResource other = (AbstractKnowledgeResource) obj;
		if (defaultDialect == null) {
			if (other.defaultDialect != null) {
				return false;
			}
		} else if (!defaultDialect.equals(other.defaultDialect)) {
			return false;
		}
		if (defaultDialectType == null) {
			if (other.defaultDialectType != null) {
				return false;
			}
		} else if (!defaultDialectType.equals(other.defaultDialectType)) {
			return false;
		}
		if (defaultEnvironment == null) {
			if (other.defaultEnvironment != null) {
				return false;
			}
		} else if (!defaultEnvironment.equals(other.defaultEnvironment)) {
			return false;
		}
		if (defaultLanguage == null) {
			if (other.defaultLanguage != null) {
				return false;
			}
		} else if (!defaultLanguage.equals(other.defaultLanguage)) {
			return false;
		}
		if (defaultReceiver == null) {
			if (other.defaultReceiver != null) {
				return false;
			}
		} else if (!defaultReceiver.equals(other.defaultReceiver)) {
			return false;
		}
		if (defaultSender == null) {
			if (other.defaultSender != null) {
				return false;
			}
		} else if (!defaultSender.equals(other.defaultSender)) {
			return false;
		}
		if (defaultSystem == null) {
			if (other.defaultSystem != null) {
				return false;
			}
		} else if (!defaultSystem.equals(other.defaultSystem)) {
			return false;
		}
		return true;
	}

}
