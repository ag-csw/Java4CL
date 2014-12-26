package elevation;

import krhashmap.AbstractKnowledgeAsset;
import krhashmap.AbstractKnowledgeEncoding;
import krhashmap.AbstractKnowledgeExpression;
import krhashmap.AbstractKnowledgeItem;
import krhashmap.AbstractKnowledgeManifestationG;
import api4kbj.ImmutableEnvironment;
import api4kbj.KnowledgeResource;

public interface Lifter {

	public AbstractKnowledgeAsset conceptualize(KnowledgeResource kr,
			ImmutableEnvironment e);

	public <T> AbstractKnowledgeAsset conceptualize(
			AbstractKnowledgeManifestationG<T> kr, ImmutableEnvironment e);

	public <T, S> AbstractKnowledgeAsset conceptualize(
			AbstractKnowledgeEncoding<T, S> kr, ImmutableEnvironment e);

	public <T, S, R> AbstractKnowledgeAsset conceptualize(
			AbstractKnowledgeItem<T, S, R> kr, ImmutableEnvironment e);

	public <T> AbstractKnowledgeExpression parse(
			AbstractKnowledgeManifestationG<T> kr);

	public <T, S> AbstractKnowledgeExpression parse(
			AbstractKnowledgeEncoding<T, S> kr);

	public <T, S, R> AbstractKnowledgeExpression parse(
			AbstractKnowledgeItem<T, S, R> kr);

	public <T, S> AbstractKnowledgeManifestationG<T> decode(
			AbstractKnowledgeEncoding<T, S> kr);

	public <T, S, R> AbstractKnowledgeManifestationG<T> decode(
			AbstractKnowledgeItem<T, S, R> kr);

	public <T, S, R> AbstractKnowledgeEncoding<T, S> prototype(
			AbstractKnowledgeItem<T, S, R> kr);

}