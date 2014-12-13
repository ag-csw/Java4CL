package api4kb;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractKnowledgeAsset extends AbstractKnowledgeResource implements KnowledgeAsset {

	private final GraphImmutableEnvironment environment;
	private final HashMap<AbstractKRRLanguage, AbstractKnowledgeExpression> mapExpression = new HashMap<AbstractKRRLanguage, AbstractKnowledgeExpression>();
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	// initializing only constructor
	AbstractKnowledgeAsset(GraphImmutableEnvironment env) {
		LOG.debug("Starting initializing asset construtor with environment: {}", env);
		this.environment = env;
	}
    
	// lazy lifting constructor
	protected AbstractKnowledgeAsset(
			AbstractKnowledgeExpression expression,
			GraphImmutableEnvironment env) {
		this(env);
		LOG.debug("Starting lazy lifting asset construtor with expression: {}", expression);
		initialValue = expression;
		mapExpressionSafePut(expression);
	}

	private void mapExpressionSafePut(AbstractKnowledgeExpression expression) {
		mapExpression.put(expression.getLanguage(), expression);		
	}

	@Override
	public KnowledgeSourceLevel getLevel() {
		return level;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	// clear memoization cache of the express method for the particular dialect
	public void clearExpress(KRRLanguage lang) {
		// TODO verify that this doesn't put the object into an inconsistent state
		mapExpression.remove(lang);

	}

	@Override
	public ImmutableEnvironment getEnvironment() {
		return environment;
	}

	// lowering method accepts a parameter indicating the language
	public AbstractKnowledgeExpression express(KRRLanguage lang)
			throws LanguageIncompatibleException {
		LOG.debug("Starting express with language: {}", lang);
		if (!environment.containsLanguage(lang)){
			throw new LanguageIncompatibleException("Requested language is not contained in environment:" + lang.toString());
		}
		if ((initialValue != null) && (initialValue.getLevel() == KnowledgeSourceLevel.EXPRESSION)){
			LOG.debug("Using cached intial value for express: {}", initialValue);
			return (AbstractKnowledgeExpression) initialValue;
		}
		// TODO implement mapping application from environment
		return null;
	}
	
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
