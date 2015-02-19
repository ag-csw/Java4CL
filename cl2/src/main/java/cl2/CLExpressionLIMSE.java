package cl2;

import elevation.Lifter;
import elevation.Lowerer;
import krconfigured.BasicKnowledgeAssetConfigured;
import krconfigured.KnowledgeResourceConfiguredTemplate;
import krhashmap.li.mse.AbstractBasicKnowledgeExpressionLIMSE;
import api4kbj.KRRDialect;
import api4kbj.KRRLanguage;

public abstract class CLExpressionLIMSE extends
		AbstractBasicKnowledgeExpressionLIMSE implements CLKnowledgeResource {

	// base non-lazy constructor
	// used by structure-based constructors
	public CLExpressionLIMSE(KnowledgeResourceConfiguredTemplate template,
			KRRLanguage lang) {
		super(template, CL.LANG);
	}

	// lazy lowering constructor - language must be specificed
	public CLExpressionLIMSE(BasicKnowledgeAssetConfigured kr, KRRLanguage lang) {
		super(kr, CL.LANG);
	}

	// lazy lifting constructor
	// TODO extend to other levels lower than manifestation
	public CLExpressionLIMSE(CLManifestation manifestation) {
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
