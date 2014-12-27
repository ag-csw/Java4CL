package elevation;

import krhashmap.AbstractBasicKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeAsset;

public interface SelfLoweringAsset extends SelfLowering, KnowledgeAsset {

	AbstractBasicKnowledgeExpression express();

	AbstractBasicKnowledgeExpression express(KRRLanguage lang);

}
