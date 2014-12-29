package krhashmap;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.AbstractKRRLanguage;
import api4kbj.ImmutableEnvironment;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeResource;
import api4kbj.KnowledgeSourceLevel;
import elevation.SelfLoweringAsset;
import graphenvironment.GraphImmutableEnvironment;

public abstract class AbstractKnowledgeAsset extends
		AbstractKnowledgeResourceLI implements KnowledgeAsset,
		SelfLoweringAsset {

	protected final GraphImmutableEnvironment environment;
	protected final HashMap<AbstractKRRLanguage, AbstractBasicKnowledgeExpression> mapExpression = new HashMap<AbstractKRRLanguage, AbstractBasicKnowledgeExpression>();
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	// initializing only constructor
	AbstractKnowledgeAsset(GraphImmutableEnvironment env, Boolean isBasic) {
		super(isBasic, KnowledgeSourceLevel.ASSET, env);
		LOG.debug(
				"Starting initializing asset construtor with environment: {}",
				env);
		this.environment = env;
	}

	// lazy lifting constructor - arguments are an Expression and environment
	protected AbstractKnowledgeAsset(KnowledgeResource initialValue,
			GraphImmutableEnvironment env) {
		// call lazy intializing constructor of super class
		super(initialValue, KnowledgeSourceLevel.ASSET, env);
		LOG.debug("Starting lazy lifting asset construtor with expression: {}",
				initialValue);
		this.environment = env;
		LOG.debug("Check that initialValue is a basic expression: {}",
				initialValue.isBasic());
		// TODO move to Basic
		// if (initialValue.isBasic()) {
		// mapExpressionSafePut(initialValue);
		// }
	}

	// TODO move to Basic class
	private void mapExpressionSafePut(AbstractKnowledgeExpression expression) {
		LOG.debug("Starting expression safeput", expression);
		if (expression.isBasic()) {
			LOG.debug("Verified expression is basic.");
			AbstractBasicKnowledgeExpression bexpression = (AbstractBasicKnowledgeExpression) expression;
			mapExpression.put((AbstractKRRLanguage) bexpression.language(),
					bexpression);
		}
	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	// TODO move to Basic class
	// clear memoization cache of the express method for the particular dialect
	public void clearExpress(KRRLanguage lang) {
		// TODO verify that this doesn't put the object into an inconsistent
		// state
		mapExpression.remove(lang);

	}

	@Override
	public ImmutableEnvironment environment() {
		return environment;
	}

	// TODO move to Basic class
	// lowering method with a parameter indicating the language
	@Override
	public AbstractBasicKnowledgeExpression express(KRRLanguage lang) {
		LOG.debug("Starting express with language: {}", lang);
		if (!environment.containsLanguage(lang)) {
			throw new IllegalArgumentException(
					"Requested language is not contained in environment:"
							+ lang);
		}
		// TODO consider replacing level check with instanceof
		if ((initialValue != null)
				&& (initialValue instanceof AbstractBasicKnowledgeExpression)) {
			AbstractBasicKnowledgeExpression expression = (AbstractBasicKnowledgeExpression) initialValue;
			LOG.debug("Found cached intial value for expression: {}",
					expression);
			if (expression.language() == lang) {
				LOG.debug("Using cached intial value");
				return expression;
			}
		}
		if (mapExpression.containsKey(lang)) {
			LOG.debug("Found cached expression in this language");
			AbstractBasicKnowledgeExpression expression = mapExpression
					.get(lang);
			LOG.debug("Using cached expression: {}", expression);
			return expression;
		}
		LOG.debug("Found no cached expression for: {}", lang);
		// Last resort: create a new expression
		return newExpression(lang);
	}

	// eager lowering
	abstract AbstractBasicKnowledgeExpression newExpression(KRRLanguage lang);

	// { return new AbstractKnowledgeExpression((AbstractKRRLanguage) lang){};

	// TODO move to Basic class
	// verify that some other equivalent property has been set
	// before forgetting initial value, to avoid leaving object
	// in inconsistent "state".
	@Override
	public void clearInitialValue() {
		if (!mapExpression.isEmpty()) {
			super.unsafeClearInitialValue();
		}
	}

}
