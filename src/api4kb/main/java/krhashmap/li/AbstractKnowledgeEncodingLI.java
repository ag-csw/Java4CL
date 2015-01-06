package krhashmap.li;

import krconfigured.KnowledgeResourceConfigured;
import krconfigured.KnowledgeResourceConfiguredTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KnowledgeEncoding;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractKnowledgeEncodingLI extends
		AbstractKnowledgeResourceLI implements KnowledgeEncoding {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	protected static final Logger SLOG = LoggerFactory
			.getLogger(AbstractKnowledgeEncodingLI.class);

	// base non-lazy constructor
	public AbstractKnowledgeEncodingLI(KnowledgeResourceConfiguredTemplate template) {
		super(template, KnowledgeSourceLevel.ENCODING);
		LOG.debug("Starting base non-lazy constructor for template: {}",
				template);
	}

	// lazy initializing constructor
	public AbstractKnowledgeEncodingLI(KnowledgeResourceConfigured initialValue) {
		super(initialValue, KnowledgeSourceLevel.ENCODING);
		LOG.debug(
				"Starting lazy-initializing constructor with initialValue: {}",
				initialValue);
	}

}
