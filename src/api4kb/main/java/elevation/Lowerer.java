package elevation;

import java.util.Arrays;

import krconfigured.BasicKnowledgeExpressionConfigured;
import krconfigured.EnvironmentConfigured;
import krconfigured.KnowledgeResourceConfigured;
import krconfigured.StructuredKnowledgeAssetConfigured;
import krconfigured.StructuredKnowledgeEncodingConfigured;
import krconfigured.StructuredKnowledgeExpressionConfigured;
import krconfigured.StructuredKnowledgeManifestationConfigured;
import api4kbj.BasicKnowledgeAsset;
import api4kbj.BasicKnowledgeEncoding;
import api4kbj.BasicKnowledgeManifestation;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeEncoding;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeIO;
import api4kbj.KnowledgeManifestation;
import api4kbj.KnowledgeSourceLevel;

public interface Lowerer {

	public default Liftable lower(KnowledgeResourceConfigured kr,
			FocusedImmutableEnvironment e, KnowledgeSourceLevel level,
			Object... args) {
		switch (level) {
		case EXPRESSION:
			// TODO check arguments
			return expresser(kr, args);
		case MANIFESTATION:
			// TODO check arguments
			return manifester(kr, args);
		case ENCODING:
			// TODO check arguments
			return encoder(kr, args);
		case IO:
			// TODO check arguments
			return reproducer(kr, args);
		default:
			throw new IllegalArgumentException(
					"Cannot lower to the ASSET level.");
		}
	}

	public default KnowledgeExpression expresser(KnowledgeResourceConfigured kr,
			Object... args) {
		switch (kr.level()) {
		case IO:
			throw new IllegalArgumentException(
					"The input of type item cannot be expressed to obtain a KnowledgeExpression, it must be expressed.");
		case ENCODING:
			throw new IllegalArgumentException(
					"The input of type encoding cannot be expressed to obtain a KnowledgeExpression, it must be expressed.");
		case MANIFESTATION:
			throw new IllegalArgumentException(
					"The input of type manifestation cannot be expressed to obtain a KnowledgeExpression, it must be expressed.");
		case EXPRESSION:
			// TODO compare kr language with language requested and throw as
			// needed
			return (KnowledgeExpression) kr;
		default:
			// TODO compare environment with language requested and throw as
			// needed
			return express((KnowledgeAsset) kr, args);
		}
	}

	public default KnowledgeManifestation manifester(KnowledgeResourceConfigured kr,
			Object... args) {
		switch (kr.level()) {
		case IO:
			throw new IllegalArgumentException(
					"The input of type item cannot be expressed to obtain a KnowledgeExpression, it must be expressed.");
		case ENCODING:
			throw new IllegalArgumentException(
					"The input of type encoding cannot be expressed to obtain a KnowledgeExpression, it must be expressed.");
		case MANIFESTATION:
			// TODO compare kr dialect with dialect = args[0] requested and
			// throw as needed
			return (KnowledgeManifestation) kr;
		default:
			return manifest(
					expresser(kr, Arrays.copyOfRange(args, 1, args.length)),
					args[0]);
		}
	}

	// TODO add default implementation
	public KnowledgeEncoding encoder(EnvironmentConfigured kr, Object... args);

	// TODO add default implementation
	public KnowledgeIO reproducer(EnvironmentConfigured kr, Object... args);

	public default KnowledgeExpression express(KnowledgeAsset kr,
			Object... args) {
		if (!kr.isBasic()) {
			return structuredExpress((StructuredKnowledgeAssetConfigured) kr, args);
		}
		return basicExpress((BasicKnowledgeAsset) kr, args);

	}

	public default KnowledgeManifestation manifest(KnowledgeExpression kr,
			Object... args) {
		if (!kr.isBasic()) {
			return structuredManifest((StructuredKnowledgeExpressionConfigured) kr, args);
		}
		return basicManifest((BasicKnowledgeExpressionConfigured) kr, args);

	}

	public default KnowledgeEncoding encode(KnowledgeManifestation kr,
			Object... args) {
		if (!kr.isBasic()) {
			return structuredEncode((StructuredKnowledgeManifestationConfigured) kr, args);
		}
		return basicEncode((BasicKnowledgeManifestation) kr, args);

	}

	public KnowledgeExpression basicExpress(BasicKnowledgeAsset kr,
			Object... args);

	public KnowledgeExpression structuredExpress(StructuredKnowledgeAssetConfigured kr,
			Object... args);

	public KnowledgeManifestation basicManifest(BasicKnowledgeExpressionConfigured kr,
			Object... args);

	public KnowledgeManifestation structuredManifest(
			StructuredKnowledgeExpressionConfigured kr, Object... args);

	public KnowledgeEncoding basicEncode(BasicKnowledgeManifestation kr,
			Object... args);

	public KnowledgeEncoding structuredEncode(
			StructuredKnowledgeManifestationConfigured kr, Object... args);

	public KnowledgeIO basicReproduce(BasicKnowledgeEncoding kr,
			Object... args);

	public KnowledgeIO structuredReproduce(StructuredKnowledgeEncodingConfigured kr,
			Object... args);

}