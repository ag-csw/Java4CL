package cl2;

import krhashmap.AbstractKnowledgeEncoding;
import krhashmap.AbstractKnowledgeItem;
import api4kbj.AbstractCodecSystem;
import api4kbj.CodecSystem;
import api4kbj.ImmutableEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KRRLanguage;

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
	public ImmutableEnvironment defaultEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRLanguage defaultLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRDialect defaultDialect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRDialectType<?> defaultDialectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodecSystem<?, ?> defaultCodecSystem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object defaultReceiver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object defaultSender() {
		// TODO Auto-generated method stub
		return null;
	}

}
