package cl2;

import elevation.Lifter;
import elevation.Lowerer;
import krhashmap.li.mse.AbstractBasicKnowledgeExpressionLIMSE;
import api4kbj.BasicKnowledgeAsset;
import api4kbj.KRRDialect;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeResourceTemplate;

public abstract class CLExpression extends
		AbstractBasicKnowledgeExpressionLIMSE implements CLKnowledgeResource {

	// base non-lazy constructor
	// used by structure-based constructors
	public CLExpression(KnowledgeResourceTemplate template, KRRLanguage lang) {
		super(template, CL.lang);
	}

	// lazy lowering constructor - language must be specificed
	public CLExpression(BasicKnowledgeAsset kr, KRRLanguage lang) {
		super(kr, CL.lang);
	}

	// lazy lifting constructor
	// TODO extend to other levels lower than manifestation
	public CLExpression(CLManifestation manifestation) {
		super(manifestation);
	}

	// default self-lowering
	@Override
	public CLManifestation manifest() {
		LOG.debug("Starting default manifest of expression");
		return manifest(defaultDialect());
	}

	// self-lowering for specified dialect
	@Override
	public CLManifestation manifest(KRRDialect dialect) {
		LOG.debug("Starting manifest of expression");
		// TODO this cast may not be safe
		return (CLManifestation) super.manifest(dialect);
	}

	@Override
	public Lifter lifter() {
		return CL.lifter();
	}

	@Override
	public Lowerer lowerer() {
		return CL.lowerer();
	}

}
