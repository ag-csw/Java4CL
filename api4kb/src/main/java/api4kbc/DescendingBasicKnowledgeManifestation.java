package api4kbc;

import api4kbj.BasicKnowledgeExpression;
import api4kbj.BasicKnowledgeManifestation;
import api4kbj.DialectTypeEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;

public class DescendingBasicKnowledgeManifestation implements
		BasicKnowledgeManifestation {

	// Wrapper-based constructor
	public DescendingBasicKnowledgeManifestation(KRRDialect dialect, BasicKnowledgeExpression expression,
			DialectTypeEnvironment environment) {
		super();
		this.dialect = dialect;
		this.expression = expression;
		this.environment = environment;
	}

	private final KRRDialect dialect;
	private final BasicKnowledgeExpression expression;
	private final DialectTypeEnvironment environment;

	@Override
	public KRRDialect dialect() {
		return dialect;
	}

	@Override
	public DialectTypeEnvironment environment() {
		return environment;
	}

	@Override
	public <T> T build(KRRDialectType<T> dialectType) {
		if (environment.containsMember(dialectType)) {
			return environment.build(expression, dialectType);
		}
		throw new IllegalArgumentException("Requested dialect type"
				+ dialectType + " is not supporte");
	}

}
