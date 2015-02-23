package api4kbop;

import api4kbj.FocusedLanguageEnvironment;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeExpression;

/**
 * ExpressingAction is a mapping from a KnowledgeAsset to a KnowledgeExpression
 * in a particular KRRLanguage lang().
 * 
 * @author taraathan
 *
 * @param <T>
 */
public interface ExpressingAction<T extends KnowledgeAsset> extends
		LoweringAction<T, KnowledgeExpression> {

	KRRLanguage lang();

	@Override
	default KnowledgeExpression f(T t) {
		FocusedLanguageEnvironment env = t.environment();
		if (!env.containsMember(lang())) {
			throw new IllegalArgumentException("The environment of the asset "
					+ t + " is not capable of mapping to the language" + lang()
					+ " of this expressing action.");
		}
		KnowledgeExpression result = env.apply(t.canonicalExpression(), lang());
		return result;
	}

}
