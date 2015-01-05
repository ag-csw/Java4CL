package krhashmap;

import api4kbj.BasicKnowledgeAsset;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.BasicKnowledgeResource;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeResourceTemplate;

public abstract class AbstractBasicKnowledgeExpression extends
		AbstractKnowledgeExpression implements BasicKnowledgeExpression {

	// base non-lazy constructor
	public AbstractBasicKnowledgeExpression(KnowledgeResourceTemplate template,
			KRRLanguage lang) {
		super(template);
		LOG.debug(
				"Starting initializing-only AbstractBasicKnowledgeExpression constructor for language: {}",
				lang);
		this.lang = lang;
	}

	// No-parameter Lazy initializing constructor
	// If kr is an asset, then its default language becomes the target language
	public AbstractBasicKnowledgeExpression(BasicKnowledgeResource kr) {
		super(kr);
		this.lang = kr.defaultLanguage();
		LOG.debug(
				"Starting no-arg lazy initializing construtor with resource: {}",
				kr);
	}

	// Lazy lowering constructor - argument is an Asset
	public AbstractBasicKnowledgeExpression(BasicKnowledgeAsset kr,
			KRRLanguage lang) {
		super(kr);
		LOG.debug("Starting lazy lowering expression construtor");
		this.lang = lang;
		if (!kr.environment().containsLanguage(lang)) {
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
