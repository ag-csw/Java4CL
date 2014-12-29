package krhashmap;

import graphenvironment.GraphImmutableEnvironment;
import api4kbj.AbstractKnowledgeResource;
import api4kbj.CodecSystem;
import api4kbj.ImmutableEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeResource;
import api4kbj.KnowledgeSourceLevel;
import lazykb.LazyInitializing;

public abstract class AbstractKnowledgeResourceLI extends
		AbstractKnowledgeResource implements
		LazyInitializing<KnowledgeResource> {

	// package-private constructors for lazy initialization
	// Arguments are the initialValue, the target level and additional
	// parameters
	public AbstractKnowledgeResourceLI(KnowledgeResource initialValue,
			KnowledgeSourceLevel level, Object defaultSender,
			Object defaultReceiver, CodecSystem<?, ?> defaultSystem,
			KRRDialectType<?> defaultDialectType, KRRDialect defaultDialect,
			ImmutableEnvironment defaultEnvironment, KRRLanguage defaultLanguage) {
		// call the most general initializing constructor of the super class
		super(defaultSender, defaultReceiver, defaultSystem,
				defaultDialectType, defaultDialect, defaultEnvironment,
				defaultLanguage);
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

	// Specialized lazy initialization constructors
	AbstractKnowledgeResourceLI(AbstractKnowledgeResourceLI initialValue,
			KnowledgeSourceLevel level, KRRLanguage lang) {
		// select default values from passed language
		// TODO improve defaultSender, Receiver handling
		this(initialValue, level, System.in, System.out, lang.defaultSystem(),
				lang.defaultDialectType(), lang.defaultDialect(), lang
						.defaultEnvironment(), lang);
	}

	public AbstractKnowledgeResourceLI(KnowledgeResource initialValue,
			KnowledgeSourceLevel level, GraphImmutableEnvironment env) {
		// select default values from passed environment
		// TODO improve defaultSender, Receiver handling
		this(initialValue, level, System.in, System.out, env.defaultLanguage()
				.defaultSystem(), env.defaultLanguage().defaultDialectType(),
				env.defaultLanguage().defaultDialect(), env.defaultLanguage()
						.defaultEnvironment(), env.defaultLanguage());
	}

	// initializing only constructors for non-LI constructors
	public AbstractKnowledgeResourceLI(KnowledgeSourceLevel level,
			KRRLanguage lang) {
		super(lang);
	}

	public AbstractKnowledgeResourceLI(KnowledgeSourceLevel level,
			GraphImmutableEnvironment env) {
		super(env);
	}

	// cache for lazy initialization
	protected KnowledgeResource initialValue;

	// optional public clear cache method for forgetting initializing value
	// which can be called after some eager evaluation has been performed.
	// Implementing classes must override this method to include
	// verification that object will not be left in an inconsistent state.
	// After verification, the unsafeClearInitialValue method can be called.
	@Override
	public abstract void clearInitialValue() throws Exception;

	void unsafeClearInitialValue() {
		initialValue = null;
	}

	// package-private getter for direct access to cache
	KnowledgeResource getInitialValue() {
		return initialValue;
	}

}