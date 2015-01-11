package krhashmap.li;

import krconfigured.BasicKnowledgeAssetConfigured;
import krconfigured.BasicKnowledgeExpressionConfigured;
import krconfigured.BasicKnowledgeResourceConfigured;
import krconfigured.KnowledgeResourceConfiguredTemplate;
import api4kbj.KRRLanguage;

public abstract class AbstractBasicKnowledgeExpressionLI extends
		AbstractKnowledgeExpressionLI implements
		BasicKnowledgeExpressionConfigured {

	// base non-lazy constructor
	public AbstractBasicKnowledgeExpressionLI(
			KnowledgeResourceConfiguredTemplate template, KRRLanguage lang) {
		super(template);
		LOG.debug(
				"Starting initializing-only AbstractBasicKnowledgeExpressionLI constructor for language: {}",
				lang);
		this.lang = lang;
	}

	// No-parameter Lazy initializing constructor
	// If kr is an asset, then its default language becomes the target language
	public AbstractBasicKnowledgeExpressionLI(BasicKnowledgeResourceConfigured kr) {
		super(kr);
		this.lang = kr.defaultLanguage();
		LOG.debug(
				"Starting no-arg lazy initializing construtor with resource: {}",
				kr);
	}

	// Lazy lowering constructor - argument is an Asset
	public AbstractBasicKnowledgeExpressionLI(BasicKnowledgeAssetConfigured kr,
			KRRLanguage lang) {
		super(kr);
		LOG.debug("Starting lazy lowering expression construtor");
		this.lang = lang;
		if (!kr.environment().containsMember(lang)) {
			throw new IllegalArgumentException(
					"Requested language is not in the environment.");
		}
		LOG.debug("Language compatibility verified");
		initialValue = kr;
	}

	// protected and private fields
	// final properties
	protected KRRLanguage lang;

	@Override
	public KRRLanguage language() {
		return lang;
	}

}
