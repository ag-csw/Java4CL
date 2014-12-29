package krhashmap;

import functional.IO;
import api4kbj.AbstractCodecSystem;
import api4kbj.AbstractKRRDialectType;
import api4kbj.BasicKnowledgeEncoding;
import api4kbj.BasicKnowledgeItem;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractBasicKnowledgeItem<T, S, R> extends
		AbstractKnowledgeResourceLI implements BasicKnowledgeItem<T, S, R> {

	private AbstractBasicKnowledgeItem(AbstractKRRDialectType<T> dialectType,
			IO<S> value, AbstractCodecSystem<T, S> system) {
		super(true, KnowledgeSourceLevel.ITEM, dialectType.language());
		this.dialectType = dialectType;
		this.value = value;
		this.system = system;
	}

	private AbstractKRRDialectType<T> dialectType;
	private IO<S> value;
	private AbstractBasicKnowledgeEncoding<T, S> encoding;
	private R destination;
	private AbstractCodecSystem<T, S> system;

	@Override
	public KnowledgeSourceLevel level() {
		return level;
	}

	@Override
	public AbstractCodecSystem<T, S> codecSystem() {
		return system;
	};

	// lifting method
	abstract BasicKnowledgeEncoding<T, S> prototype();

	// action
	abstract void write();

	// action
	abstract S read();

	public abstract void clear();

	@Override
	public IO<S> value() {
		return value;
	}

	@Override
	public R destination() {
		return destination;
	}

	@Override
	public AbstractKRRDialectType<T> dialectType() {
		return dialectType;
	}

	// verify that some other equivalent property has been set
	// before forgetting initial value, to avoid leaving object
	// in inconsistent "state".
	@Override
	public void clearInitialValue() {
		if ((value != null) | (encoding != null)) {
			super.unsafeClearInitialValue();
		}
	}

}
