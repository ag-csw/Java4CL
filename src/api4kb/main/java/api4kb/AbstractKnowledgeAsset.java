package api4kb;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elevation.SelfLoweringAsset;


public abstract class AbstractKnowledgeAsset extends AbstractKnowledgeResource implements KnowledgeAsset, SelfLoweringAsset {

	private final GraphImmutableEnvironment environment;
	protected final HashMap<AbstractKRRLanguage, AbstractKnowledgeExpression> mapExpression = new HashMap<AbstractKRRLanguage, AbstractKnowledgeExpression>();
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	// initializing only constructor
	AbstractKnowledgeAsset(GraphImmutableEnvironment env) {
		LOG.debug("Starting initializing asset construtor with environment: {}", env);
		this.environment = env;
	}
    
	// lazy lifting constructor - argument is an Expression and environment
	protected AbstractKnowledgeAsset(
			AbstractKnowledgeExpression expression,
			GraphImmutableEnvironment env) {
		this(env);
		// TODO check that environment and language of expression are compatible.
		LOG.debug("Starting lazy lifting asset construtor with expression: {}", expression);
		initialValue = expression;
		mapExpressionSafePut(expression);
	}

	private void mapExpressionSafePut(AbstractKnowledgeExpression expression) {
		LOG.debug("Starting expression safeput", expression);
		AbstractKRRLanguage lang = expression.getLanguage();
		mapExpression.put(lang, expression);		
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

	// default lowering method returns an expression in the default language
	// for this environment
	@Override
	public AbstractKnowledgeExpression express() throws LanguageIncompatibleException{
			return express(environment.getDefaultLanguage());
	}

	// lowering method with a parameter indicating the language
	@Override
	public AbstractKnowledgeExpression express(KRRLanguage lang)
			throws LanguageIncompatibleException {
		LOG.debug("Starting express with language: {}", lang);
		if (!environment.containsLanguage(lang)){
			throw new LanguageIncompatibleException("Requested language is not contained in environment:" + lang);
		}
		// TODO consider replacing level check with instanceof
		if ((initialValue != null) && (initialValue.getLevel() == KnowledgeSourceLevel.EXPRESSION)){
			AbstractKnowledgeExpression expression = (AbstractKnowledgeExpression) initialValue;
			LOG.debug("Found cached intial value for expression: {}", expression);
			if (expression.getLanguage() == lang){
			  LOG.debug("Using cached intial value");
			  return expression;
			}
		}
		if (mapExpression.containsKey(lang)){			
			LOG.debug("Found cached expression in this language");
			AbstractKnowledgeExpression expression = mapExpression.get(lang);
			LOG.debug("Using cached expression: {}", expression);
            return expression;
		}
		LOG.debug("Found no cached expression for: {}", lang);
		// Last resort: create a new expression
		return newExpression(lang);
	}
	
	// eager lowering
	abstract AbstractKnowledgeExpression newExpression(KRRLanguage lang);
	//{ return new AbstractKnowledgeExpression((AbstractKRRLanguage) lang){};
	
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
	public ImmutableEnvironment getDefaultEnvironment() {
		return getEnvironment();
	}

	@Override
	public KRRLanguage getDefaultLanguage() {
		return getDefaultEnvironment().getDefaultLanguage();
	}

	@Override
	public KRRDialect getDefaultDialect() {
		return getDefaultLanguage().defaultDialect();
	}

	@Override
	public KRRDialectType<?> getDefaultDialectType() {
		return getDefaultDialect().getDefaultDialectType();
	}

	@Override
	public CodecSystem<?, ?> getDefaultCodecSystem() {
		return getDefaultDialectType().defaultSystem();
	}

	@Override
	public KRRLanguage getDefaultReceiver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRLanguage getDefaultSender() {
		// TODO Auto-generated method stub
		return null;
	}



}
