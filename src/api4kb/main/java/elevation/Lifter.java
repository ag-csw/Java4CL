package elevation;

import api4kbj.BasicKnowledgeEncoding;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.BasicKnowledgeIO;
import api4kbj.BasicKnowledgeManifestation;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeEncoding;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeIO;
import api4kbj.KnowledgeManifestation;
import api4kbj.KnowledgeSourceLevel;
import api4kbj.StructuredKnowledgeEncoding;
import api4kbj.StructuredKnowledgeExpression;
import api4kbj.StructuredKnowledgeIO;
import api4kbj.StructuredKnowledgeManifestation;

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
			return structuredConceptualize((StructuredKnowledgeExpression) kr,
					e);
		}
		return basicConceptualize((BasicKnowledgeExpression) kr, e);

	}

	default KnowledgeExpression parse(KnowledgeManifestation kr) {
		if (!kr.isBasic()) {
			return structuredParse((StructuredKnowledgeManifestation) kr);
		}
		return basicParse((BasicKnowledgeManifestation) kr);

	}

	default KnowledgeManifestation decode(KnowledgeEncoding kr) {
		if (!kr.isBasic()) {
			return structuredDecode((StructuredKnowledgeEncoding) kr);
		}
		return basicDecode((BasicKnowledgeEncoding) kr);

	}

	default KnowledgeEncoding prototype(KnowledgeIO kr) {
		if (!kr.isBasic()) {
			return structuredPrototype((StructuredKnowledgeIO) kr);
		}
		return basicPrototype((BasicKnowledgeIO) kr);

	}

	KnowledgeAsset structuredConceptualize(StructuredKnowledgeExpression kr,
			FocusedImmutableEnvironment e);

	KnowledgeAsset basicConceptualize(BasicKnowledgeExpression kr,
			FocusedImmutableEnvironment e);

	KnowledgeExpression structuredParse(StructuredKnowledgeManifestation kr);

	KnowledgeExpression basicParse(BasicKnowledgeManifestation kr);

	KnowledgeManifestation structuredDecode(StructuredKnowledgeEncoding kr);

	KnowledgeManifestation basicDecode(BasicKnowledgeEncoding kr);

	KnowledgeEncoding basicPrototype(BasicKnowledgeIO kr);

	KnowledgeEncoding structuredPrototype(StructuredKnowledgeIO kr);

}