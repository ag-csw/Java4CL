package elevation;

import krhashmap.AbstractKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeAsset;

public interface SelfLoweringAsset extends SelfLowering, KnowledgeAsset {

	AbstractKnowledgeExpression express();

	AbstractKnowledgeExpression express(KRRLanguage lang);

}
