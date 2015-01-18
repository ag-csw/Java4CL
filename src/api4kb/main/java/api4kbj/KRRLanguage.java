package api4kbj;

public interface KRRLanguage extends ClassWrapper<KnowledgeExpression> {

	KRRLogic logic();

	@Override
	default Class<? extends KnowledgeExpression> asClass() {
		try {
			@SuppressWarnings("unchecked")
			Class<? extends KnowledgeExpression> clazz = (Class<? extends KnowledgeExpression>) Class
					.forName("KnowledgeExpression");
			return clazz;
		} catch (Exception e) {
			assert false : "The class Knowledge Expression must be implemented";
		}
		return null;
	}

}
