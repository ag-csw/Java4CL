package krhashmap.li.mse;

import java.util.HashMap;

import krconfigured.BasicKnowledgeEncodingConfigured;
import krconfigured.BasicKnowledgeResourceConfigured;
import krconfigured.KnowledgeResourceConfiguredTemplate;
import krhashmap.AbstractBasicKnowledgeManifestation;
import krhashmap.li.AbstractKnowledgeEncodingLI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;

public abstract class AbstractBasicKnowledgeEncodingLIMSE extends
		AbstractKnowledgeEncodingLI implements BasicKnowledgeEncodingConfigured {
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	// base non-lazy constructor
	public AbstractBasicKnowledgeEncodingLIMSE(
			KnowledgeResourceConfiguredTemplate template, KRRFormat format) {
		super(template);
		this.format = format;
		LOG.debug("Starting base nonlazy constructor for template: {}",
				template);
	}

	// Wrapper-based constructor
	// TODO move to Abstract class that is above LI
	public <T> AbstractBasicKnowledgeEncodingLIMSE(
			KnowledgeResourceConfiguredTemplate template, T value,
			KRRFormatType<T> formatType) {
		// TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this(template, formatType.format());
		mapValueSafePut(value, formatType);
	}

	// No-parameter Lazy initializing constructor
	// If kr is an asset or expression, then its default dialect becomes the
	// target language
	public AbstractBasicKnowledgeEncodingLIMSE(BasicKnowledgeResourceConfigured kr) {
		super(kr);
		this.format = kr.defaultFormat();
		LOG.debug(
				"Starting no-arg lazy initializing construtor with resource: {}",
				kr);
	}

	// protected fields
	protected KRRFormat format;
	protected final HashMap<KRRFormatType<?>, Object> mapValue = new HashMap<KRRFormatType<?>, Object>();
	// TODO implement cache for IO
	protected AbstractBasicKnowledgeManifestation manifestation;

	protected <T> void mapValueSafePut(T value, KRRFormatType<T> formatType) {
		mapValue.put(formatType, value);
	}

	@Override
	public KRRFormat format() {
		return format;
	}

}
