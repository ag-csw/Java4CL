package krhashmap.li;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KnowledgeEncoding;
import api4kbj.KnowledgeResource;
import api4kbj.KnowledgeResourceTemplate;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractKnowledgeEncodingLI extends
		AbstractKnowledgeResourceLI implements KnowledgeEncoding {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	protected static final Logger SLOG = LoggerFactory
			.getLogger(AbstractKnowledgeEncodingLI.class);

	// base non-lazy constructor
	public AbstractKnowledgeEncodingLI(KnowledgeResourceTemplate template) {
		super(template, KnowledgeSourceLevel.ENCODING);
		LOG.debug("Starting base non-lazy constructor for template: {}",
				template);
	}

	// lazy initializing constructor
	public AbstractKnowledgeEncodingLI(KnowledgeResource initialValue) {
		super(initialValue, KnowledgeSourceLevel.ENCODING);
		LOG.debug(
				"Starting lazy-initializing constructor with initialValue: {}",
				initialValue);
	}

}
