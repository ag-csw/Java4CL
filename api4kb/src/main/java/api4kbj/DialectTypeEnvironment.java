package api4kbj;

public interface DialectTypeEnvironment extends
		Environment<KRRDialectType<?>, Object> {
	
	<T> T build(final BasicKnowledgeExpression expression, KRRDialectType<T> dialect);

	boolean isDescending();

}
