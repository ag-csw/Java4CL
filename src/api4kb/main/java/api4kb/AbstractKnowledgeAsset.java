package api4kb;

import java.util.HashMap;


public abstract class AbstractKnowledgeAsset implements KnowledgeAsset {

	private ImmutableEnvironment environment;
	private final HashMap<AbstractKRRLanguage, AbstractKnowledgeExpression> mapExpression = new HashMap<AbstractKRRLanguage, AbstractKnowledgeExpression>();

	public AbstractKnowledgeAsset() {
		// TODO Auto-generated constructor stub
	}
	
	public AbstractKnowledgeAsset(
			AbstractKnowledgeExpression expression,
			ImmutableEnvironment e) {
		mapExpressionSafePut(expression);
		this.environment = e;
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
	public GraphImmutableEnvironment getEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	// lowering method accepts a parameter indicating the language
	public AbstractKnowledgeExpression express(KRRLanguage lang)
			throws LanguageIncompatibleException {
		// TODO Auto-generated method stub
		return null;
	}

}
