package api4kbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fj.F;
import functional.EqSet;
import functional.EqSetPolyTree;
import api4kba.AbstractBasicKnowledgeExpression;
import api4kba.AbstractBasicKnowledgeExpressionLike;
import api4kbj.KRRLanguage;
import api4kbj.StructuredKnowledgeExpression;

public class EqSetStructuredKnowledgeExpressionComposite<E extends AbstractBasicKnowledgeExpression>  implements StructuredKnowledgeExpression  {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	private EqSetStructuredKnowledgeExpressionComposite(final EqSet<KRRLanguage> languages,
			final EqSetPolyTree<E> components) {
		this.languages = languages;
		this.components = components;
	}

	private EqSetStructuredKnowledgeExpressionComposite(
			final EqSetPolyTree<E> components) {
		this(components.map(s -> AbstractBasicKnowledgeExpressionLike.language_(s) ).toLeftEqSet(), components);
	}

	private final EqSet<KRRLanguage> languages;
	private final EqSetPolyTree<E> components;
	
	//factory methods
	public static <A extends AbstractBasicKnowledgeExpression> EqSetStructuredKnowledgeExpressionComposite<A> ke(final EqSetPolyTree<A> components){
		return new EqSetStructuredKnowledgeExpressionComposite<A>(components);
	}

	@Override
	public EqSetPolyTree<E> components() {
		return components;
	}

	public static <A extends AbstractBasicKnowledgeExpression> EqSetPolyTree<A> components(final EqSetStructuredKnowledgeExpressionComposite<A> s) {
		return s.components();
	}

	// first-class version of components(x)
	public static <A extends AbstractBasicKnowledgeExpression> F<EqSetStructuredKnowledgeExpressionComposite<A>, EqSetPolyTree<A>> components_() {
		return s -> components(s);
	}

	@Override
	public int numComponents() {
		return components.size();
	}

	@Override
	public EqSet<KRRLanguage> languages() {
		return languages;
	}

}
