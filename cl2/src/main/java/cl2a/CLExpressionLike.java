package cl2a;

import api4kba.AbstractBasicKnowledgeExpressionLike;
import cl2.CL;
import cl2i.CLImmutable;

public abstract class CLExpressionLike extends AbstractBasicKnowledgeExpressionLike
  implements CLImmutable{

	public CLExpressionLike() {
		super(CL.LANG);
	}
	
	@Override
	public abstract CLExpressionLike copy();
	

}
