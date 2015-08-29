package cl2a;

import api4kba.AbstractBasicKnowledgeExpressionLike;
import cl2.CL;

public abstract class CLExpressionLike extends AbstractBasicKnowledgeExpressionLike  {

	public CLExpressionLike() {
		super(CL.LANG);
	}
	
	@Override
	public abstract CLExpressionLike copy();
	

}
