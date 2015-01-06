package krhashmap.li;

import krconfigured.BasicKnowledgeItemConfigured;
import krconfigured.BasicKnowledgeResourceConfigured;
import functional.IO;
import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;
import api4kbj.KnowledgeResourceTemplate;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractBasicKnowledgeItemLI extends
		AbstractKnowledgeResourceLI implements BasicKnowledgeItemConfigured {

	// base non-lazy constructor
	public AbstractBasicKnowledgeItemLI(KnowledgeResourceTemplate template,
			KRRFormat format, Object destination) {
		super(template, KnowledgeSourceLevel.ITEM);
		this.format = format;
		this.destination = destination;
	}

	// Wrapper-based constructor
	// TODO move to Abstract class that is above LI
	public <T> AbstractBasicKnowledgeItemLI(KnowledgeResourceTemplate template,
			IO<T> value, KRRFormatType<T> formatType) {
		// TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this(template, formatType.format(), value.destination());
	}

	// No-parameter Lazy initializing constructor
	// If kr is an asset or expression, then its default dialect becomes the
	// target language
	public AbstractBasicKnowledgeItemLI(BasicKnowledgeResourceConfigured kr) {
		super(kr, KnowledgeSourceLevel.ITEM);
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
