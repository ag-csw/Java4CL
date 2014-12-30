package api4kbj;

public interface BasicKnowledgeEncoding extends KnowledgeEncoding,
		BasicKnowledgeResource {

	<T, S> BasicKnowledgeEncodingG<T, S> build(KRRDialectType<T> dialectType,
			CodecSystem<T, S> codecSystem);

}
