import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.runner.RunWith;

import api4kbj.BasicKnowledgeExpression;
import api4kbj.KnowledgeExpression;
import api4kbj7.IKRRLogic;
import api4kbja.AbstractBasicKnowledgeExpression;
import api4kbja.AbstractKRRLanguage;
import api4kbjc.API4KB;

@RunWith(Parameterized.class)
public class BasicKnowledgeExpressionTest {
	public BasicKnowledgeExpression expression;

	public BasicKnowledgeExpressionTest(BasicKnowledgeExpression expression) {
		this.expression = expression;
	}

	@Test
	public void expressionAndItsLanguageSatisfyUsesLanguageRelation() {
		assertTrue(API4KB.usesLanguage(expression, expression.language()));
	}

	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		IKRRLogic logicA = new IKRRLogic() {

			@Override
			public String name() {
				return "Logic A";
			}
		};
		IKRRLogic logicB = new IKRRLogic() {

			@Override
			public String name() {
				return "Logic B";
			}
		};
		BasicKnowledgeExpression expression0 = new AbstractBasicKnowledgeExpression(
				new AbstractKRRLanguage("Language One", logicA) {

					@Override
					public Class<? extends KnowledgeExpression> asClass() {
						return KnowledgeExpression.class;
					}
				}) {
		};
		BasicKnowledgeExpression expression1 = new AbstractBasicKnowledgeExpression(
				new AbstractKRRLanguage("Language Two", logicA) {

					@Override
					public Class<? extends KnowledgeExpression> asClass() {
						return KnowledgeExpression.class;
					}
				}) {
		};
		return Arrays
				.asList(new Object[][] { { expression0 }, { expression1 } });
	}
}
