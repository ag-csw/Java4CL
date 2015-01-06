package krhashmap.li;

import krconfigured.KnowledgeResourceConfigured;
import krconfigured.KnowledgeResourceConfiguredTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KnowledgeManifestation;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractKnowledgeManifestationLI extends
		AbstractKnowledgeResourceLI implements KnowledgeManifestation {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	protected static final Logger SLOG = LoggerFactory
			.getLogger(AbstractKnowledgeManifestationLI.class);

	// base non-lazy constructor
	public AbstractKnowledgeManifestationLI(
			KnowledgeResourceConfiguredTemplate template) {
		super(template, KnowledgeSourceLevel.MANIFESTATION);
		LOG.debug("Starting base non-lazy constructor for template: {}",
				template);
	}

	// lazy initializing constructor
	public AbstractKnowledgeManifestationLI(
			KnowledgeResourceConfigured initialValue) {
		super(initialValue, KnowledgeSourceLevel.MANIFESTATION);
		LOG.debug(
				"Starting lazy-initializing constructor with initialValue: {}",
				initialValue);
	}

	// TODO move to LIMSE abstract class
	protected AbstractKnowledgeExpressionLI expression;

}
