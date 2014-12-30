package elevation;

import api4kbj.KnowledgeManifestation;
import api4kbj.KnowledgeAsset;
import api4kbj.BasicKnowledgeEncodingG;
import api4kbj.KnowledgeExpression;
import api4kbj.BasicKnowledgeItem;
import api4kbj.BasicKnowledgeManifestationG;
import api4kbj.ImmutableEnvironment;
import api4kbj.KnowledgeResource;
import api4kbj.KnowledgeSourceLevel;

public interface Lifter {

	public default KnowledgeResource lift(KnowledgeResource kr,
			ImmutableEnvironment e, KnowledgeSourceLevel level) {
		switch (level) {
		case ASSET:
			return conceptualize(kr, e);
		case EXPRESSION:
			return parse(kr);
			// case MANIFESTATION:
			// return decode(kr);
			// case ENCODING:
			// return prototype(kr);
		default:
			break;
		}
		return null;
	}

	public default KnowledgeAsset conceptualize(KnowledgeResource kr,
			ImmutableEnvironment e) {
		switch (kr.level()) {
		case ASSET:
			return (KnowledgeAsset) kr;
		default:
			return conceptualize(kr, e);
		}
	}

	public default KnowledgeExpression parse(KnowledgeResource kr) {
		switch (kr.level()) {
		case ASSET:
			throw new IllegalArgumentException(
					"The input of type asset cannot be parsed to obtain a KnowledgeExpression, it must be expressed.");
		case EXPRESSION:
			return (KnowledgeExpression) kr;
		default:
			return parse(kr);
		}
	}

	public KnowledgeAsset conceptualize(KnowledgeExpression kr,
			ImmutableEnvironment e);

	public KnowledgeAsset conceptualize(KnowledgeManifestation kr,
			ImmutableEnvironment e);

	public <T> KnowledgeAsset conceptualize(BasicKnowledgeManifestationG<T> kr,
			ImmutableEnvironment e);

	public <T, S> KnowledgeAsset conceptualize(
			BasicKnowledgeEncodingG<T, S> kr, ImmutableEnvironment e);

	public <T, S, R> KnowledgeAsset conceptualize(
			BasicKnowledgeItem<T, S, R> kr, ImmutableEnvironment e);

	public KnowledgeExpression parse(KnowledgeManifestation kr);

	public <T> KnowledgeExpression parse(BasicKnowledgeManifestationG<T> kr);

	public <T, S> KnowledgeExpression parse(BasicKnowledgeEncodingG<T, S> kr);

	public <T, S, R> KnowledgeExpression parse(BasicKnowledgeItem<T, S, R> kr);

	public <T, S> KnowledgeManifestation decode(BasicKnowledgeEncodingG<T, S> kr);

	public <T, S, R> KnowledgeManifestation decode(
			BasicKnowledgeItem<T, S, R> kr);

	public <T, S> BasicKnowledgeManifestationG<T> decodeG(
			BasicKnowledgeEncodingG<T, S> kr);

	public <T, S, R> BasicKnowledgeManifestationG<T> decodeG(
			BasicKnowledgeItem<T, S, R> kr);

	public <T, S, R> BasicKnowledgeEncodingG<T, S> prototype(
			BasicKnowledgeItem<T, S, R> kr);

}