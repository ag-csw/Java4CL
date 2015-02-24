package api4kbc;

import fj.Ord;
import fj.data.Set;
import api4kbj.KRRLanguage;
import api4kbj.StructuredKnowledgeExpression;

public class FJSetStructuredKnowledgeExpression extends FJSetKnowledgeExpression implements StructuredKnowledgeExpression  {

	public FJSetStructuredKnowledgeExpression(Set<KRRLanguage> languages,
			Set<FJSetKnowledgeExpression> components) {
		super(languages);
		this.components = components;
	}

	public FJSetStructuredKnowledgeExpression(
			Set<FJSetKnowledgeExpression> components) {
		this(components.bind(Ord.hashEqualsOrd(), s -> languages(s) ), components);
	}

	private Set<FJSetKnowledgeExpression> components;

	@Override
	public Set<FJSetKnowledgeExpression> components() {
		return components;
	}

	@Override
	public int numComponents() {
		return components.size();
	}

}
