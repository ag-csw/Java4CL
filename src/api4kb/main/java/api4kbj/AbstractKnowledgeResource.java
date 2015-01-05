package api4kbj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKnowledgeResource implements KnowledgeResource {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKnowledgeResource(Object defaultSender,
			Object defaultReceiver, CodecSystem<?, ?> defaultSystem,
			KRRFormatType<?> defaultFormatType, KRRFormat defaultFormat,
			KRRDialectType<?> defaultDialectType, KRRDialect defaultDialect,
			FocusedImmutableEnvironment defaultEnvironment,
			KRRLanguage defaultLanguage) {
		this.defaultSender = defaultSender;
		this.defaultReceiver = defaultReceiver;
		this.defaultSystem = defaultSystem;
		this.defaultFormatType = defaultFormatType;
		this.defaultFormat = defaultFormat;
		this.defaultDialectType = defaultDialectType;
		this.defaultDialect = defaultDialect;
		this.defaultEnvironment = defaultEnvironment;
		this.defaultLanguage = defaultLanguage;
	}

	private Object defaultSender;
	private Object defaultReceiver;
	private CodecSystem<?, ?> defaultSystem;
	private KRRFormatType<?> defaultFormatType;
	private KRRFormat defaultFormat;
	private KRRDialectType<?> defaultDialectType;
	private KRRDialect defaultDialect;
	private FocusedImmutableEnvironment defaultEnvironment;
	private KRRLanguage defaultLanguage;

	@Override
	public FocusedImmutableEnvironment defaultEnvironment() {
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
	public KRRFormat defaultFormat() {
		return defaultFormat;
	}

	@Override
	public KRRFormatType<?> defaultFormatType() {
		return defaultFormatType;
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
