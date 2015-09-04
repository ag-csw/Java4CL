
package api4kba;

import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;

public abstract class AbstractBasicKnowledgeExpression extends AbstractBasicKnowledgeExpressionLike implements
		BasicKnowledgeExpression {


	public AbstractBasicKnowledgeExpression(final KRRLanguage language) {
	 super(language);
	}

}
