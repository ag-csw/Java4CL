package krhashmap.li;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KnowledgeManifestation;
import api4kbj.KnowledgeResource;
import api4kbj.KnowledgeResourceTemplate;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractKnowledgeManifestationLI extends
		AbstractKnowledgeResourceLI implements KnowledgeManifestation {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	protected static final Logger SLOG = LoggerFactory
			.getLogger(AbstractKnowledgeManifestationLI.class);

	// base non-lazy constructor
	public AbstractKnowledgeManifestationLI(KnowledgeResourceTemplate template) {
		super(template, KnowledgeSourceLevel.MANIFESTATION);
		LOG.debug("Starting base non-lazy constructor for template: {}",
				template);
	}

	// lazy initializing constructor
	public AbstractKnowledgeManifestationLI(KnowledgeResource initialValue) {
		super(initialValue, KnowledgeSourceLevel.MANIFESTATION);
		LOG.debug(
				"Starting lazy-initializing constructor with initialValue: {}",
				initialValue);
	}

	// TODO move to LIMSE abstract class
	protected AbstractKnowledgeExpressionLI expression;

}
