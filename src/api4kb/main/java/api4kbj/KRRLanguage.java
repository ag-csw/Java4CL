package api4kbj;

public interface KRRLanguage {

	String name();

	KRRLogic logic();

	default Class<? extends KnowledgeExpression> asClass() {
		try {
			return (Class<? extends KnowledgeExpression>) Class.forName("KnowledgeExpression");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
