package api4kbj;

import java.io.InputStream;

import krconfigured.EnvironmentConfigured;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public final class API4KB {

	API4KB() {
	}

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public static CodecSystem<Element, InputStream> CodecSystemXMLUTF8 = new CodecSystem<Element, InputStream>() {

		@Override
		public InputStream code(Element t) {
			// TODO
			return null;
		}

		@Override
		public Element decode(InputStream s) {
			// TODO
			return null;
		}

	};

	public static Boolean hasSnapshot(MutableSource m, ImmutableSource i) {
		return m.getSnapshot().equals(i);
	}

	public static Boolean lifts(EnvironmentConfigured krup,
			EnvironmentConfigured krdown) {
		// TODO implement using an Operation
		// In the case that krup is an Asset, then refer to the environment of
		// krup
		EnvironmentConfigured krup2 = null;
		return krup.equals(krup2);
	}

	public static Boolean lowers(EnvironmentConfigured krdown,
			EnvironmentConfigured krup) {
		return lifts(krup, krdown);
	}

	public static Boolean reproduces(KnowledgeItem i, KnowledgeEncoding e) {
		return lowers(i, e);
	}

	public static Boolean prototypes(KnowledgeEncoding e, KnowledgeItem i) {
		return lifts(e, i);
	}

	public static Boolean encodes(KnowledgeEncoding e, KnowledgeManifestation m) {
		return lowers(e, m);
	}

	public static Boolean decodes(KnowledgeManifestation m, KnowledgeEncoding e) {
		return lifts(m, e);
	}

	public static Boolean parses(KnowledgeExpression e, KnowledgeManifestation m) {
		return lifts(e, m);
	}

	public static Boolean manifests(KnowledgeManifestation m,
			KnowledgeExpression e) {
		return lowers(m, e);
	}

	public static Boolean conceptualizes(KnowledgeAsset a, KnowledgeExpression e) {
		return lifts(a, e);
	}

	public static Boolean expresses(KnowledgeExpression e, KnowledgeAsset a) {
		return lowers(e, a);
	}

}
