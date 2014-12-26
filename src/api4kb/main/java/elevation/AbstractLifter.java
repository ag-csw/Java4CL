package elevation;

import krhashmap.AbstractKnowledgeAsset;
import krhashmap.AbstractKnowledgeEncoding;
import krhashmap.AbstractKnowledgeExpression;
import krhashmap.AbstractKnowledgeItem;
import krhashmap.AbstractKnowledgeManifestationG;
import api4kbj.ImmutableEnvironment;
import api4kbj.KnowledgeResource;

public abstract class AbstractLifter implements Lifter {

	public AbstractKnowledgeAsset conceptualize(KnowledgeResource kr) {
		return conceptualize(kr, kr.defaultEnvironment());
	}

	@Override
	public abstract <T> AbstractKnowledgeAsset conceptualize(
			AbstractKnowledgeManifestationG<T> kr, ImmutableEnvironment e);

	@Override
	public abstract <T, S> AbstractKnowledgeAsset conceptualize(
			AbstractKnowledgeEncoding<T, S> kr, ImmutableEnvironment e);

	@Override
	public abstract <T, S, R> AbstractKnowledgeAsset conceptualize(
			AbstractKnowledgeItem<T, S, R> kr, ImmutableEnvironment e);

	@Override
	public abstract <T> AbstractKnowledgeExpression parse(
			AbstractKnowledgeManifestationG<T> kr);

	@Override
	public abstract <T, S> AbstractKnowledgeExpression parse(
			AbstractKnowledgeEncoding<T, S> kr);

	@Override
	public abstract <T, S, R> AbstractKnowledgeExpression parse(
			AbstractKnowledgeItem<T, S, R> kr);

	@Override
	public abstract <T, S> AbstractKnowledgeManifestationG<T> decode(
			AbstractKnowledgeEncoding<T, S> kr);

	@Override
	public abstract <T, S, R> AbstractKnowledgeManifestationG<T> decode(
			AbstractKnowledgeItem<T, S, R> kr);

	@Override
	public abstract <T, S, R> AbstractKnowledgeEncoding<T, S> prototype(
			AbstractKnowledgeItem<T, S, R> kr);

}
