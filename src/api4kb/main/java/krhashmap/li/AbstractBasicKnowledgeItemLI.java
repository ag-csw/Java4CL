package krhashmap.li;

import krconfigured.BasicKnowledgeIOConfigured;
import krconfigured.BasicKnowledgeResourceConfigured;
import krconfigured.KnowledgeResourceConfiguredTemplate;
import functional.IO;
import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractBasicKnowledgeItemLI extends
		AbstractKnowledgeResourceLI implements BasicKnowledgeIOConfigured {

	// base non-lazy constructor
	public AbstractBasicKnowledgeItemLI(KnowledgeResourceConfiguredTemplate template,
			KRRFormat format, Object destination) {
		super(template, KnowledgeSourceLevel.IO);
		this.format = format;
		this.destination = destination;
	}

	// Wrapper-based constructor
	// TODO move to Abstract class that is above LI
	public <T> AbstractBasicKnowledgeItemLI(KnowledgeResourceConfiguredTemplate template,
			IO<T> value, KRRFormatType<T> formatType) {
		// TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this(template, formatType.format(), value.destination());
	}

	// No-parameter Lazy initializing constructor
	// If kr is an asset or expression, then its default dialect becomes the
	// target language
	public AbstractBasicKnowledgeItemLI(BasicKnowledgeResourceConfigured kr) {
		super(kr, KnowledgeSourceLevel.IO);
		LOG.debug(
				"Starting no-arg lazy initializing construtor with resource: {}",
				kr);
		this.format = kr.defaultFormat();
		this.destination = kr.defaultReceiver();
	}

	protected KRRFormat format;
	private final Object destination;

	@Override
	public Object destination() {
		return destination;
	}

	@Override
	public KRRFormat format() {
		return format;
	}

}
