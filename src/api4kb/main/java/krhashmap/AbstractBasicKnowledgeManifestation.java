package krhashmap;

import java.util.HashMap;

import api4kbj.BasicKnowledgeEncoding;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.BasicKnowledgeResource;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.BasicKnowledgeManifestation;
import api4kbj.KRRFormat;
import api4kbj.KnowledgeResourceTemplate;

public abstract class AbstractBasicKnowledgeManifestation extends
		AbstractKnowledgeManifestationLI implements BasicKnowledgeManifestation {

	// base non-lazy constructor
	public AbstractBasicKnowledgeManifestation(
			KnowledgeResourceTemplate template) {
		super(template);
		LOG.debug("Starting base nonlazy constructor for template: {}",
				template);
	}

	// Wrapper-based constructor
	// TODO move to Abstract class that is above LI
	public <T> AbstractBasicKnowledgeManifestation(
			KnowledgeResourceTemplate template, T value,
			KRRDialectType<T> dialectType) {
		// TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this(template);
		mapValueSafePut(value, dialectType);
	}

	// No-parameter Lazy initializing constructor
	// If kr is an asset or expression, then its default dialect becomes the
	// target language
	public AbstractBasicKnowledgeManifestation(BasicKnowledgeResource kr) {
		super(kr);
		this.dialect = kr.defaultDialect();
		LOG.debug(
				"Starting no-arg lazy initializing construtor with resource: {}",
				kr);
	}

	// protected fields
	// final properties
	protected KRRDialect dialect;
	protected final HashMap<KRRDialectType<?>, Object> mapValue = new HashMap<KRRDialectType<?>, Object>();
	// TODO move caches for lifting and lowering methods to LISME
	protected final HashMap<KRRFormat, BasicKnowledgeEncoding> mapEncoding = new HashMap<KRRFormat, BasicKnowledgeEncoding>();
	protected BasicKnowledgeExpression expression;

	//

	protected <T> void mapValueSafePut(T value, KRRDialectType<T> dialectType) {
		mapValue.put(dialectType, value);
	}

	@Override
	public KRRDialect dialect() {
		return dialect;
	}

	@Override
	public void clearInitialValue() throws Exception {
		// TODO move to LIMSE check other caches to see if it is OK to clear the
		// initial value
	}

}
