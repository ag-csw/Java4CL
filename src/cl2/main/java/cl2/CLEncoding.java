package cl2;

import api4kb.AbstractKnowledgeEncoding;
import api4kb.AbstractCodecSystem;
import api4kb.AbstractKnowledgeItem;
import api4kb.CodecSystem;
import api4kb.ImmutableEnvironment;
import api4kb.KRRDialect;
import api4kb.KRRDialectType;
import api4kb.KRRLanguage;

public class CLEncoding<T, S> extends AbstractKnowledgeEncoding<T, S> implements
		CLKnowledgeResource {

	public CLEncoding(S stream, CLDialectType<T> dialect,
			AbstractCodecSystem<T, S> system) {
		super(stream, dialect, system);
	}

	public CLEncoding(CLManifestationG<T> manifestation,
			AbstractCodecSystem<T, S> system) {
		super(manifestation, system);
	}

	// static factory methods
	public static <T, S> CLEncoding<T, S> lazyNewInstance(
			CLManifestationG<T> manifestation, AbstractCodecSystem<T, S> system) {
		return new CLEncoding<T, S>(manifestation, system);
	}

	@Override
	public <R> AbstractKnowledgeItem<T, S, R> reproduce(R destination) {
		// TODO implement eager lowering to item
		// Case 1. from encoding
		// Case 2. from manifestation
		// Case 3. from expression
		return null;
	}

	@Override
	public CLManifestationG<T> decode() {
		return (CLManifestationG<T>) super.decode();
	}

	@Override
	public ImmutableEnvironment getDefaultEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRLanguage getDefaultLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRDialect getDefaultDialect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRDialectType<?> getDefaultDialectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodecSystem<?, ?> getDefaultCodecSystem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDefaultReceiver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDefaultSender() {
		// TODO Auto-generated method stub
		return null;
	}

}
