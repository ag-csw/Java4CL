package krhashmap.li;

import krconfigured.KnowledgeResourceConfigured;
import krconfigured.KnowledgeResourceConfiguredTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractKnowledgeExpressionLI extends
		AbstractKnowledgeResourceLI implements KnowledgeExpression {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	protected static final Logger SLOG = LoggerFactory
			.getLogger(AbstractKnowledgeExpressionLI.class);

	// base non-lazy constructor
	public AbstractKnowledgeExpressionLI(
			KnowledgeResourceConfiguredTemplate template) {
		super(template, KnowledgeSourceLevel.EXPRESSION);
		LOG.debug("Starting base non-lazy constructor for template: {}",
				template);
	}

	// lazy initializing constructor
	public AbstractKnowledgeExpressionLI(
			KnowledgeResourceConfigured initialValue) {
		super(initialValue, KnowledgeSourceLevel.EXPRESSION);
		LOG.debug(
				"Starting lazy-initializing constructor with initialValue: {}",
				initialValue);
	}

}