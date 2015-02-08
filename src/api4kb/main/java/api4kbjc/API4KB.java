package api4kbjc;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import api4kbj.CodecSystem;
import api4kbj.FocusedImmutableLanguageEnvironment;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeEncoding;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeIO;
import api4kbj.KnowledgeManifestation;
import api4kbj.KnowledgeResource;
import api4kbj7.IImmutableSource;
import api4kbj7.IKnowledgeExpression;
import api4kbj7.IMutableSource;
import api4kbj7.ev.IPublicEvent;
import api4kbjpro.Proficiency;

public final class API4KB {

	API4KB() {
	}

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public static boolean accordingTo(KnowledgeAsset asset,
			FocusedImmutableLanguageEnvironment environment) {
		return asset.accordingTo(environment);
	}

	public static boolean actualizes(IPublicEvent event, Proficiency proficiency) {
		return event.actualizes(proficiency);
	}

	public static Boolean conceptualizes(KnowledgeAsset a, IKnowledgeExpression e) {
		return a.conceptualizes(e);
	}

	public static boolean usesLanguage(IKnowledgeExpression expression,
			KRRLanguage language) {
		return expression.usesLanguage(language);
	}

	// TODO configure below

	public static Boolean hasSnapshot(IMutableSource m, IImmutableSource i) {
		return m.getSnapshot().equals(i);
	}

	public static Boolean lifts(KnowledgeResource krup, KnowledgeResource krdown) {
		// TODO implement using an Operation
		// In the case that krup is an Asset, then refer to the environment of
		// krup
		KnowledgeResource krup2 = null;
		return krup.equals(krup2);
	}

	public static Boolean lowers(KnowledgeResource krdown,
			KnowledgeResource krup) {
		return lifts(krup, krdown);
	}

	public static Boolean reproduces(KnowledgeIO i, KnowledgeEncoding e) {
		return lowers(i, e);
	}

	public static Boolean prototypes(KnowledgeEncoding e, KnowledgeIO i) {
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

	public static Boolean expresses(KnowledgeExpression e, KnowledgeAsset a) {
		return lowers(e, a);
	}

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

}
