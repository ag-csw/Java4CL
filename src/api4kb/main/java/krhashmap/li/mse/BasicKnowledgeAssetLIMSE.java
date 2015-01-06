package krhashmap.li.mse;

import java.util.HashMap;

import krconfigured.EnvironmentConfigured;
import krhashmap.AbstractBasicKnowledgeExpression;
import krhashmap.li.AbstractKnowledgeExpressionLI;
import krhashmap.li.BasicKnowledgeAssetLI;
import api4kbj.AbstractKRRLanguage;
import api4kbj.BasicKnowledgeResource;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeSourceLevel;
import elevation.Liftable;
import elevation.Lowerer;
import elevation.SelfLowering;

public class BasicKnowledgeAssetLIMSE extends BasicKnowledgeAssetLI implements
		SelfLowering {

	public BasicKnowledgeAssetLIMSE(BasicKnowledgeResource kr,
			FocusedImmutableEnvironment environment) {
		super(kr, environment);
		// TODO need switch on level or a more generic cache
		// because the initialValue might not be an expression
		mapExpressionSafePut((AbstractKnowledgeExpressionLI) initialValue);
	}

	protected final HashMap<AbstractKRRLanguage, AbstractBasicKnowledgeExpression> mapExpression = new HashMap<AbstractKRRLanguage, AbstractBasicKnowledgeExpression>();

	private void mapExpressionSafePut(AbstractKnowledgeExpressionLI expression) {
		LOG.debug("Starting expression safeput", expression);
		if (expression.isBasic()) {
			LOG.debug("Verified expression is basic.");
			AbstractBasicKnowledgeExpression bexpression = (AbstractBasicKnowledgeExpression) expression;
			mapExpression.put((AbstractKRRLanguage) bexpression.language(),
					bexpression);
		}
	}

	// clear memoization cache of the express method for the particular dialect
	public void clearExpress(KRRLanguage lang) {
		// TODO verify that this doesn't put the object into an inconsistent
		// state
		mapExpression.remove(lang);

	}

	AbstractBasicKnowledgeExpression newExpression(KRRLanguage lang) {
		// TODO LOG
		// TODO check that expression is not null
		AbstractBasicKnowledgeExpression expression = mapExpression.values()
				.iterator().next();
		return (AbstractBasicKnowledgeExpression) environment().translate(
				expression, lang);
	}

	public AbstractKnowledgeExpressionLI express() {
		return express(defaultLanguage());
	}

	public EnvironmentConfigured lower() {
		return lower(KnowledgeSourceLevel.EXPRESSION);
	}

	public EnvironmentConfigured lower(KnowledgeSourceLevel level) {
		switch (level) {
		case ASSET:
			return this;
		case EXPRESSION:
			return express();
		default:
			return null;
		}
	}

	// lowering method with a parameter indicating the language
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

	@Override
	public Lowerer lowerer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Liftable lower(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

}
