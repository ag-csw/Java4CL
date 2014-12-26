package elevation;

import api4kb.AbstractKnowledgeExpression;
import api4kb.KRRLanguage;
import api4kb.KnowledgeAsset;

public interface SelfLoweringAsset extends SelfLowering, KnowledgeAsset {

	AbstractKnowledgeExpression express();

	AbstractKnowledgeExpression express(KRRLanguage lang);

}
