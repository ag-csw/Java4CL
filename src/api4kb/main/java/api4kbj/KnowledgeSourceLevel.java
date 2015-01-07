package api4kbj;

public enum KnowledgeSourceLevel {
	IO, ENCODING, MANIFESTATION, EXPRESSION, ASSET;

	private static KnowledgeSourceLevel byOrdinal(int i) {
		switch (i) {
		case (0):
			return IO;
		case (1):
			return ENCODING;
		case (2):
			return MANIFESTATION;
		case (3):
			return EXPRESSION;
		case (4):
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
		return !equals(IO);
	}
}
