package krhashmap;

import api4kbj.BasicKnowledgeEncoding;
import api4kbj.BasicKnowledgeItem;
import api4kbj.BasicKnowledgeManifestationG;
import api4kbj.ImmutableEnvironment;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeManifestation;
import api4kbj.KnowledgeResource;
import api4kbj.KnowledgeSourceLevel;
import elevation.Lifter;

public abstract class AbstractLifter implements Lifter {

	@Override
	public AbstractKnowledgeResource lift(KnowledgeResource kr,
			ImmutableEnvironment e, KnowledgeSourceLevel level) {
		if (kr instanceof AbstractKnowledgeResource) {
			return (AbstractKnowledgeResource) Lifter.super.lift(kr, e, level);
		}
		throw new IllegalArgumentException("Input" + kr.toString()
				+ " is not an AbstractKnowledgeResource");
	}

	@Override
	public KnowledgeAsset conceptualize(KnowledgeResource kr,
			ImmutableEnvironment e) {
		// TODO Auto-generated method stub
		return Lifter.super.conceptualize(kr, e);
	}

	@Override
	public KnowledgeExpression parse(KnowledgeResource kr) {
		// TODO Auto-generated method stub
		return Lifter.super.parse(kr);
	}

	@Override
	public KnowledgeAsset conceptualize(KnowledgeExpression kr,
			ImmutableEnvironment e) {
		return ((AbstractBasicKnowledgeExpression) e).conceptualize();
	}

	@Override
	public KnowledgeAsset conceptualize(KnowledgeManifestation kr,
			ImmutableEnvironment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> KnowledgeAsset conceptualize(BasicKnowledgeManifestationG<T> kr,
			ImmutableEnvironment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, S> KnowledgeAsset conceptualize(BasicKnowledgeEncoding<T, S> kr,
			ImmutableEnvironment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, S, R> KnowledgeAsset conceptualize(
			BasicKnowledgeItem<T, S, R> kr, ImmutableEnvironment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KnowledgeExpression parse(KnowledgeManifestation kr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> KnowledgeExpression parse(BasicKnowledgeManifestationG<T> kr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, S> KnowledgeExpression parse(BasicKnowledgeEncoding<T, S> kr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, S, R> KnowledgeExpression parse(BasicKnowledgeItem<T, S, R> kr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, S> KnowledgeManifestation decode(BasicKnowledgeEncoding<T, S> kr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, S, R> KnowledgeManifestation decode(
			BasicKnowledgeItem<T, S, R> kr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, S> BasicKnowledgeManifestationG<T> decodeG(
			BasicKnowledgeEncoding<T, S> kr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, S, R> BasicKnowledgeManifestationG<T> decodeG(
			BasicKnowledgeItem<T, S, R> kr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, S, R> BasicKnowledgeEncoding<T, S> prototype(
			BasicKnowledgeItem<T, S, R> kr) {
		// TODO Auto-generated method stub
		return null;
	}

}
