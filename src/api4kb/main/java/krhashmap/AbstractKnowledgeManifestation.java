package krhashmap;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.AbstractCodecSystem;
import api4kbj.AbstractKRRDialect;
import api4kbj.AbstractKRRDialectType;
import api4kbj.KRRDialect;
import api4kbj.KnowledgeManifestation;

public abstract class AbstractKnowledgeManifestation extends
		AbstractKnowledgeResource implements KnowledgeManifestation {

	// Initializing-only constructor
	public AbstractKnowledgeManifestation(AbstractKRRDialect dialect) {
		LOG.debug("Starting initializing constructor for dialect: {}", dialect);
		this.dialect = dialect;
	}

	// Wrapper-based constructor
	public <T> AbstractKnowledgeManifestation(T value,
			AbstractKRRDialectType<T> dialectType) {
		// TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this(dialectType.getDialect());
		mapValueSafePut(value, dialectType);
	}

	// protected fields
	// final properties
	protected final AbstractKRRDialect dialect;
	protected final HashMap<AbstractKRRDialectType<?>, AbstractKnowledgeManifestationG<?>> mapValue = new HashMap<AbstractKRRDialectType<?>, AbstractKnowledgeManifestationG<?>>();
	// cache for lifting and lowering methods
	protected final HashMap<AbstractCodecSystem<?, ?>, AbstractKnowledgeEncoding<?, ?>> mapEncoding = new HashMap<AbstractCodecSystem<?, ?>, AbstractKnowledgeEncoding<?, ?>>();
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
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public KRRDialect getDialect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearInitialValue() {
		// TODO Auto-generated method stub

	}

}
