package elevation;

import api4kb.AbstractKnowledgeExpression;
import api4kb.KRRLanguage;
import api4kb.KnowledgeAsset;
import api4kb.LanguageIncompatibleException;

public interface SelfLoweringAsset extends SelfLowering, KnowledgeAsset {

	AbstractKnowledgeExpression express() throws LanguageIncompatibleException;

	AbstractKnowledgeExpression express(KRRLanguage lang)
			throws LanguageIncompatibleException;

}
