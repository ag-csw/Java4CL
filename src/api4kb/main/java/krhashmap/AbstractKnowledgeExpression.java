package krhashmap;

import graphenvironment.GraphImmutableEnvironment;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.ImmutableEnvironment;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractKnowledgeExpression extends
		AbstractKnowledgeResourceLI implements KnowledgeExpression {

	protected final HashMap<ImmutableEnvironment, AbstractKnowledgeAsset> mapAsset = new HashMap<ImmutableEnvironment, AbstractKnowledgeAsset>();
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	protected static final Logger SLOG = LoggerFactory
			.getLogger(AbstractKnowledgeExpression.class);

	// partial constructors for non-lazy concrete constructors
	public AbstractKnowledgeExpression(Boolean isBasic, KRRLanguage lang) {
		super(KnowledgeSourceLevel.EXPRESSION, lang);
		LOG.debug("Starting abstract constructor with isBasic: {}", isBasic);
	}

	public AbstractKnowledgeExpression(boolean isBasic,
			GraphImmutableEnvironment env) {
		super(KnowledgeSourceLevel.EXPRESSION, env);
	}

	// lazy initializing constructor
	public AbstractKnowledgeExpression(AbstractKnowledgeResourceLI initialValue) {
		super(initialValue, KnowledgeSourceLevel.EXPRESSION, initialValue
				.defaultLanguage());
		LOG.debug("Starting abstract lazy constructor with initialValue: {}",
				initialValue);
	}

	public AbstractKnowledgeExpression(
			AbstractKnowledgeResourceLI initialValue,
			GraphImmutableEnvironment env) {
		super(initialValue, KnowledgeSourceLevel.EXPRESSION, env);
		LOG.debug("Starting abstract lazy constructor with initialValue: {}",
				initialValue);
	}

	protected void assetSafePut(AbstractKnowledgeAsset asset) {
		mapAsset.put(asset.environment(), asset);
	}

	public void clear() {
		clearAsset();
	}

	public void clearAsset() {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapAsset.clear();
	}

	public void clearConceptualize(ImmutableEnvironment environment) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapAsset.remove(environment);
	}

}