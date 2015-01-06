package krconfigured;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.CodecSystem;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;
import api4kbj.KRRLanguage;

public abstract class AbstractKnowledgeResourceConfigured implements KnowledgeResourceConfigured {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public AbstractKnowledgeResourceConfigured(InputStream defaultSender,
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

	private InputStream defaultSender;
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
	public InputStream defaultSender() {
		return defaultSender;
	}

}
