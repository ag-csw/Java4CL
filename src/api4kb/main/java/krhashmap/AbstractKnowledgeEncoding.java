package krhashmap;

import java.io.Console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.AbstractCodecSystem;
import api4kbj.AbstractKRRDialectType;
import api4kbj.KnowledgeEncoding;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractKnowledgeEncoding<T, S> extends
		AbstractKnowledgeResource implements KnowledgeEncoding<T, S> {
	// Initializing-only constructor
	public AbstractKnowledgeEncoding(AbstractKRRDialectType<T> dialectType,
			AbstractCodecSystem<T, S> system) {
		this.dialectType = dialectType;
		this.system = system;
	}

	// Wrapper-based constructor
	public AbstractKnowledgeEncoding(S value,
			AbstractKRRDialectType<T> dialectType,
			AbstractCodecSystem<T, S> system) {
		this(dialectType, system);
		this.value = value;
	}

	// Lazy lowering constructor - argument is manifestation and encoding system
	public AbstractKnowledgeEncoding(
			AbstractKnowledgeManifestationG<T> manifestation,
			AbstractCodecSystem<T, S> system) {
		this(manifestation.dialectType(), system);
		this.manifestation = manifestation;
	}

	// Lazy lifting constructor - argument is a KnowledgeItem
	public <R> AbstractKnowledgeEncoding(AbstractKnowledgeItem<T, S, R> input) {
		this(input.dialectType(), input.codecSystem());
		// TODO this is not lazy
		this.value = input.read();
	}

	// protected fields
	protected S value;
	protected final AbstractKRRDialectType<T> dialectType;
	protected final AbstractCodecSystem<T, S> system;
	protected AbstractKnowledgeManifestationG<T> manifestation;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public KnowledgeSourceLevel level() {
		return level;
	}

	@Override
	public S value() {
		// add get for lazy constructor from Item
		if (value == null) {
			if (!(manifestation == null)) {
				value = manifestation.encode(system).value();
			} else {
				assert false : "Inconsistent state";
			}
		}
		return value;
	}

	@Override
	public AbstractKRRDialectType<T> dialectType() {
		return dialectType;
	}

	@Override
	public AbstractCodecSystem<T, S> codecSystem() {
		return system;
	}

	// default lowering method returns an item for the default receiver
	// which is the console for this implementation
	public AbstractKnowledgeItem<T, S, Console> reproduce() {
		return reproduce(System.console());
	}

	// lowering method
	public <R> AbstractKnowledgeItem<T, S, R> reproduce(R destination) {
		LOG.debug("Starting encode of manifestation");
		LOG.debug("  Codec system: {}", system);
		LOG.debug("  Dialect of the manifestation: {}", dialectType);
		LOG.debug("  Language of the expression: {}", dialectType.language());
		// TODO consider replacing level check with instanceof
		if ((initialValue != null)
				&& (initialValue.level() == KnowledgeSourceLevel.ITEM)) {
			@SuppressWarnings("unchecked")
			AbstractKnowledgeItem<T, S, ?> encoding = (AbstractKnowledgeItem<T, S, ?>) initialValue;
			LOG.debug("Found cached intial value for encoding: {}", encoding);
			if (encoding.codecSystem() == system) {
				LOG.debug("Using cached intial value");
				return (AbstractKnowledgeItem<T, S, R>) encoding;
			}
		}

		return null;

	}

	// lifting method
	public AbstractKnowledgeManifestationG<T> decode() {
		if (manifestation == null) {
			// TODO implement
			return manifestation;
		} else {
			return manifestation;
		}

	}

	// clear memoization cache of the decode method
	public void clearDecode() {
		// If the manifestation cache is empty, do nothing
		if (manifestation != null) {
			// Before clearing the expression cache, be sure that this
			// will not put the object into an invalid state
			if (value == null) {
				value = value();
			}
			manifestation = null;
		}
	}

	@Override
	public void clear() {
		clearDecode();
	}

	// verify that some other equivalent property has been set
	// before forgetting initial value, to avoid leaving object
	// in inconsistent "state".
	@Override
	public void clearInitialValue() {
		if ((value != null) | (manifestation != null)) {
			super.unsafeClearInitialValue();
		}
	}

}
