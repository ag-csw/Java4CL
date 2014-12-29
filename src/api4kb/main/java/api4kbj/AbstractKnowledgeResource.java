package api4kbj;

import graphenvironment.GraphImmutableEnvironment;

public abstract class AbstractKnowledgeResource implements KnowledgeResource {

	public AbstractKnowledgeResource(Boolean isBasic,
			KnowledgeSourceLevel level, KRRLanguage lang) {
		this(isBasic, level, System.in, System.out, lang.defaultSystem(), lang
				.defaultDialectType(), lang.defaultDialect(), lang
				.defaultEnvironment(), lang);
	}

	public AbstractKnowledgeResource(Boolean isBasic,
			KnowledgeSourceLevel level, Object defaultSender,
			Object defaultReceiver, CodecSystem<?, ?> defaultSystem,
			KRRDialectType<?> defaultDialectType, KRRDialect defaultDialect,
			ImmutableEnvironment defaultEnvironment, KRRLanguage defaultLanguage) {
		this.level = level;
		this.defaultSender = defaultSender;
		this.defaultReceiver = defaultReceiver;
		this.defaultSystem = defaultSystem;
		this.defaultDialectType = defaultDialectType;
		this.defaultDialect = defaultDialect;
		this.defaultEnvironment = defaultEnvironment;
		this.defaultLanguage = defaultLanguage;
	}

	public AbstractKnowledgeResource(Boolean isBasic,
			KnowledgeSourceLevel level, GraphImmutableEnvironment env) {
		this(isBasic, level, System.in, System.out, env.defaultLanguage()
				.defaultSystem(), env.defaultLanguage().defaultDialectType(),
				env.defaultLanguage().defaultDialect(), env, env
						.defaultLanguage());
	}

	private KnowledgeSourceLevel level;
	private Object defaultSender;
	private Object defaultReceiver;
	private CodecSystem<?, ?> defaultSystem;
	private KRRDialectType<?> defaultDialectType;
	private KRRDialect defaultDialect;
	private ImmutableEnvironment defaultEnvironment;
	private KRRLanguage defaultLanguage;

	@Override
	public KnowledgeSourceLevel level() {
		return level;
	}

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

}
