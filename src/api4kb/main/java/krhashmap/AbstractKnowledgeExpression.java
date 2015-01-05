package krhashmap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeResource;
import api4kbj.KnowledgeResourceTemplate;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractKnowledgeExpression extends
		AbstractKnowledgeResourceLI implements KnowledgeExpression {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	protected static final Logger SLOG = LoggerFactory
			.getLogger(AbstractKnowledgeExpression.class);

	// base non-lazy constructor
	public AbstractKnowledgeExpression(KnowledgeResourceTemplate template) {
		super(template, KnowledgeSourceLevel.EXPRESSION);
		LOG.debug("Starting base non-lazy constructor for template: {}",
				template);
	}

	// lazy initializing constructor
	public AbstractKnowledgeExpression(KnowledgeResource initialValue) {
		super(initialValue, KnowledgeSourceLevel.EXPRESSION);
		LOG.debug(
				"Starting lazy-initializing constructor with initialValue: {}",
				initialValue);
	}

}