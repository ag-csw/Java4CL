package krhashmap;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.AbstractCodecSystem;
import api4kbj.AbstractKRRDialect;
import api4kbj.AbstractKRRDialectType;
import api4kbj.BasicKnowledgeManifestation;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractBasicKnowledgeManifestation extends
		AbstractKnowledgeResourceLI implements BasicKnowledgeManifestation {

	// Initializing-only constructor
	public AbstractBasicKnowledgeManifestation(AbstractKRRDialect dialect) {
		super(true, KnowledgeSourceLevel.MANIFESTATION, dialect.language());
		LOG.debug("Starting initializing constructor for dialect: {}", dialect);
		this.dialect = dialect;
	}

	// Wrapper-based constructor
	public <T> AbstractBasicKnowledgeManifestation(T value,
			AbstractKRRDialectType<T> dialectType) {
		// TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this(dialectType.dialect());
		mapValueSafePut(value, dialectType);
	}

	// protected fields
	// final properties
	protected final AbstractKRRDialect dialect;
	protected final HashMap<AbstractKRRDialectType<?>, AbstractBasicKnowledgeManifestationG<?>> mapValue = new HashMap<AbstractKRRDialectType<?>, AbstractBasicKnowledgeManifestationG<?>>();
	// cache for lifting and lowering methods
	protected final HashMap<AbstractCodecSystem<?, ?>, AbstractBasicKnowledgeEncoding<?, ?>> mapEncoding = new HashMap<AbstractCodecSystem<?, ?>, AbstractBasicKnowledgeEncoding<?, ?>>();
	protected AbstractKnowledgeExpression expression;
	//
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	protected abstract <T> void mapValueSafePut(T value,
			AbstractKRRDialectType<T> dialectType);

	/*
	 * { AbstractKnowledgeManifestationG<T> manifest = new
	 * AbstractKnowledgeManifestationG<T>(value, dialectType){
	 * 
	 * }
	 */

	@Override
	public AbstractKRRDialect dialect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T value() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearInitialValue() {
		// TODO Auto-generated method stub

	}

}
