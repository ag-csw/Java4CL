package krhashmap.li;

import java.io.InputStream;

import krconfigured.BasicKnowledgeIOConfigured;
import krconfigured.BasicKnowledgeResourceConfigured;
import krconfigured.KnowledgeResourceConfiguredTemplate;
import functional.IO;
import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractBasicKnowledgeIOLI extends
		AbstractKnowledgeResourceLI implements BasicKnowledgeIOConfigured {

	// base non-lazy constructor
	public AbstractBasicKnowledgeIOLI(KnowledgeResourceConfiguredTemplate template,
			KRRFormat format, InputStream input) {
		super(template, KnowledgeSourceLevel.IO);
		this.format = format;
		this.input = input;
	}

	// Wrapper-based constructor
	// TODO move to Abstract class that is above LI
	public <T, R extends InputStream> AbstractBasicKnowledgeIOLI(KnowledgeResourceConfiguredTemplate template,
			IO<R> value, KRRFormatType<T> formatType) {
		// TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this(template, formatType.format(), value.input());
	}

	// No-parameter Lazy initializing constructor
	// If kr is an asset or expression, then its default dialect becomes the
	// target language
	public AbstractBasicKnowledgeIOLI(BasicKnowledgeResourceConfigured kr) {
		super(kr, KnowledgeSourceLevel.IO);
		LOG.debug(
				"Starting no-arg lazy initializing construtor with resource: {}",
				kr);
		this.format = kr.defaultFormat();
		this.input = kr.defaultInput();
	}

	protected KRRFormat format;
	private final InputStream input;

	@Override
	public InputStream input() {
		return input;
	}

	@Override
	public KRRFormat format() {
		return format;
	}

}
