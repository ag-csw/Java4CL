package krhashmap.li;

import krconfigured.KnowledgeResourceConfigured;
import api4kbj.AbstractKnowledgeResource;
import api4kbj.CodecSystem;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeResourceTemplate;
import api4kbj.KnowledgeSourceLevel;
import lazykb.LazyInitializing;

public abstract class AbstractKnowledgeResourceLI extends
		AbstractKnowledgeResource implements
		LazyInitializing<KnowledgeResourceConfigured> {

	// template constructor uses properties of template but does not set it as
	// initial value
	public AbstractKnowledgeResourceLI(KnowledgeResourceTemplate template,
			KnowledgeSourceLevel level) {
		// call the most general initializing constructor of the super class
		super(template.defaultSender(), template.defaultReceiver(), template
				.defaultCodecSystem(), template.defaultFormatType(), template
				.defaultFormat(), template.defaultDialectType(), template
				.defaultDialect(), template.defaultEnvironment(), template
				.defaultLanguage());
	}

	// Specialized lazy initialization constructors
	public AbstractKnowledgeResourceLI(KnowledgeResourceConfigured initialValue,
			KnowledgeSourceLevel level) {
		// select default values from passed initialValue
		// TODO improve defaultSender, Receiver handling
		this(initialValue, level, initialValue.defaultSender(), initialValue
				.defaultReceiver(), initialValue.defaultCodecSystem(),
				initialValue.defaultFormatType(), initialValue.defaultFormat(),
				initialValue.defaultDialectType(), initialValue
						.defaultDialect(), initialValue.defaultEnvironment(),
				initialValue.defaultLanguage());
	}

	// package-private constructors for lazy initialization
	// Arguments are the initialValue, the target level and additional
	// parameters
	public AbstractKnowledgeResourceLI(KnowledgeResourceConfigured initialValue,
			KnowledgeSourceLevel level, Object defaultSender,
			Object defaultReceiver, CodecSystem<?, ?> defaultSystem,
			KRRFormatType<?> defaultFormatType, KRRFormat defaultFormat,
			KRRDialectType<?> defaultDialectType, KRRDialect defaultDialect,
			FocusedImmutableEnvironment defaultEnvironment,
			KRRLanguage defaultLanguage) {
		// call the most general initializing constructor of the super class
		super(defaultSender, defaultReceiver, defaultSystem, defaultFormatType,
				defaultFormat, defaultDialectType, defaultDialect,
				defaultEnvironment, defaultLanguage);
		// TODO check that defaults and the initial value are
		// compatible. If not, throw a runtime exception.
		// <li>defaultEnvironment.contains(initialValue.defaultEnvironment()))
		if (!defaultEnvironment.contains(initialValue.defaultEnvironment())) {
			throw new IllegalArgumentException(
					"The specified default environment does not contain the default environment of the initial value.");
		}
		// <li>defaultEnvironment.contains(initialValue.defaultLanguage()))
		// <li>if initialValue.defaultLanguage is not equal to defaultLanguage,
		// then level must be ASSET
		// <li>if initialValue.defaultDialect is not equal to defaultDialect,
		// the level must be EXPRESSION or ASSET
		// set the initial value field
		this.initialValue = initialValue;
	}

	// cache for lazy initialization
	protected KnowledgeResourceConfigured initialValue;

	// optional public clear cache method for forgetting initializing value
	// which can be called after some eager evaluation has been performed.
	// Implementing classes must override this method to include
	// verification that object will not be left in an inconsistent state.
	// After verification, the unsafeClearInitialValue method can be called.
	@Override
	public abstract void clearInitialValue() throws Exception;

	protected void unsafeClearInitialValue() {
		initialValue = null;
	}

	// package-private getter for direct access to cache
	KnowledgeResourceConfigured getInitialValue() {
		return initialValue;
	}

}