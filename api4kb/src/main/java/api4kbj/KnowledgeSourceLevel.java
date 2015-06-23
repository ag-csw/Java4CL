package api4kbj;

public enum KnowledgeSourceLevel {
	ENCODING, MANIFESTATION, EXPRESSION, ASSET;

	private static KnowledgeSourceLevel byOrdinal(final int i) {
		switch (i) {
		case (0):
			return ENCODING;
		case (1):
			return MANIFESTATION;
		case (2):
			return EXPRESSION;
		case (3):
			return ASSET;
		default:
			throw new IllegalArgumentException(
					"There is no knowledge source level corresponding to ordinal "
							+ i);
		}
	}

	public KnowledgeSourceLevel succ() {
		return KnowledgeSourceLevel.byOrdinal(this.ordinal() + 1);
	}

	public KnowledgeSourceLevel pred() {
		return KnowledgeSourceLevel.byOrdinal(this.ordinal() - 1);
	}

	public boolean hasSucc() {
		return !equals(ASSET);
	}

	public boolean hasPred() {
		return !equals(ENCODING);
	}
}
