package elevation;

import krconfigured.BasicKnowledgeEncodingConfigured;
import krconfigured.BasicKnowledgeExpressionConfigured;
import krconfigured.BasicKnowledgeIOConfigured;
import krconfigured.StructuredKnowledgeEncodingConfigured;
import krconfigured.StructuredKnowledgeExpressionConfigured;
import krconfigured.StructuredKnowledgeIOConfigured;
import krconfigured.StructuredKnowledgeManifestationConfigured;
import api4kbj.BasicKnowledgeManifestation;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeEncoding;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeIO;
import api4kbj.KnowledgeManifestation;
import api4kbj.KnowledgeSourceLevel;

public interface Lifter {

	default Lowerable lift(Liftable kr, FocusedImmutableEnvironment e,
			KnowledgeSourceLevel level) {
		switch (level) {
		case ASSET:
			return conceptualizer(kr, e);
		case EXPRESSION:
			return parser(kr);
		case MANIFESTATION:
			return decoder(kr);
		case ENCODING:
			return prototyper(kr);
		default:
			throw new IllegalArgumentException("Cannot lift to the IO level.");
		}
	}

	default KnowledgeAsset conceptualizer(Liftable kr,
			FocusedImmutableEnvironment e) {
		switch (kr.level()) {
		case ASSET:
			// TODO message
			throw new IllegalArgumentException("");
		default:
			return conceptualize(parser(kr), e);
		}
	}

	default KnowledgeExpression parser(Liftable kr) {
		switch (kr.level()) {
		case ASSET:
			throw new IllegalArgumentException(
					"The input of type asset cannot be parsed to obtain a KnowledgeExpression, it must be expressed.");
		case EXPRESSION:
			// TODO compare language and construct or throw as needed
			return (KnowledgeExpression) kr;
		default:
			return parse(decoder(kr));
		}
	}

	// TODO add default implementation
	KnowledgeManifestation decoder(Liftable kr);

	// TODO add default implementation
	KnowledgeEncoding prototyper(Liftable kr);

	default KnowledgeAsset conceptualize(KnowledgeExpression kr,
			FocusedImmutableEnvironment e) {
		if (!kr.isBasic()) {
			return structuredConceptualize((StructuredKnowledgeExpressionConfigured) kr,
					e);
		}
		return basicConceptualize((BasicKnowledgeExpressionConfigured) kr, e);

	}

	default KnowledgeExpression parse(KnowledgeManifestation kr) {
		if (!kr.isBasic()) {
			return structuredParse((StructuredKnowledgeManifestationConfigured) kr);
		}
		return basicParse((BasicKnowledgeManifestation) kr);

	}

	default KnowledgeManifestation decode(KnowledgeEncoding kr) {
		if (!kr.isBasic()) {
			return structuredDecode((StructuredKnowledgeEncodingConfigured) kr);
		}
		return basicDecode((BasicKnowledgeEncodingConfigured) kr);

	}

	default KnowledgeEncoding prototype(KnowledgeIO kr) {
		if (!kr.isBasic()) {
			return structuredPrototype((StructuredKnowledgeIOConfigured) kr);
		}
		return basicPrototype((BasicKnowledgeIOConfigured) kr);

	}

	KnowledgeAsset structuredConceptualize(StructuredKnowledgeExpressionConfigured kr,
			FocusedImmutableEnvironment e);

	KnowledgeAsset basicConceptualize(BasicKnowledgeExpressionConfigured kr,
			FocusedImmutableEnvironment e);

	KnowledgeExpression structuredParse(StructuredKnowledgeManifestationConfigured kr);

	KnowledgeExpression basicParse(BasicKnowledgeManifestation kr);

	KnowledgeManifestation structuredDecode(StructuredKnowledgeEncodingConfigured kr);

	KnowledgeManifestation basicDecode(BasicKnowledgeEncodingConfigured kr);

	KnowledgeEncoding basicPrototype(BasicKnowledgeIOConfigured kr);

	KnowledgeEncoding structuredPrototype(StructuredKnowledgeIOConfigured kr);

}