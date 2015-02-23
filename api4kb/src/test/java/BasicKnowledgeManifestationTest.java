import static org.junit.Assert.*;
import hashenvironment.HashKRRDialectTypeEnvironment;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import api4kba.AbstractKRRDialect;
import api4kba.AbstractKRRDialectType;
import api4kba.AbstractKRRLanguage;
import api4kbc.API4KB;
import api4kbc.WrapperBasicKnowledgeManifestation;
import api4kbj.BasicKnowledgeManifestation;
import api4kbj.DialectTypeEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KRRLanguage;
import api4kbj.KRRLogic;
import api4kbj.KnowledgeExpression;

@RunWith(Parameterized.class)
public class BasicKnowledgeManifestationTest {

	public BasicKnowledgeManifestationTest(
			BasicKnowledgeManifestation manifestation, KRRDialect dialect,
			KRRDialectType<?> dialectType, Object value) {
		super();
		this.manifestation = manifestation;
		this.dialect = dialect;
		this.dialectType = dialectType;
		this.value = value;
	}


	public BasicKnowledgeManifestation manifestation;
	public KRRDialect dialect;
	public KRRDialectType<?> dialectType;
	public Object value;

	@Test
	public void manifestationShouldBeBasic() {
		assertTrue(manifestation.isBasic());
	}

	@Test
	public void manifestationDialectShouldBeAsConstructed() {
		assertEquals(manifestation.dialect(), dialect);
	}

	@Test
	public void manifestationAndItsDialectShouldSatisfyUsesDialectRelation() {
		assertTrue(API4KB.usesDialect(manifestation, manifestation.dialect()));
		assertTrue(manifestation.usesDialect(manifestation.dialect()));
	}

	@Test
	public void manifestationValueShouldBeAsConstructed() {
		assertEquals(manifestation.build(dialectType), value);
	}


	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		KRRLogic logicA = new KRRLogic() {

			@Override
			public String name() {
				return "Logic A";
			}
		};
		KRRLogic logicB = new KRRLogic() {

			@Override
			public String name() {
				return "Logic B";
			}
		};

		KRRLanguage lang0 = new AbstractKRRLanguage("Language Zero", logicA) {

			@Override
			public Class<? extends KnowledgeExpression> asClass() {
				return KnowledgeExpression.class;
			};
		};
		KRRLanguage lang1 = new AbstractKRRLanguage("Language One", logicA) {

			@Override
			public Class<? extends KnowledgeExpression> asClass() {
				return KnowledgeExpression.class;
			};
		};
		KRRLanguage lang2 = new AbstractKRRLanguage("Language Two", logicB) {

			@Override
			public Class<? extends KnowledgeExpression> asClass() {
				return KnowledgeExpression.class;
			};
		};

		String dialectName0 = "Dialect Zero";
		KRRDialect dialect0 = new AbstractKRRDialect(dialectName0, lang0) {
		};
		String dialectTypeName0 = "Dialect Type Zero";
		Class<String> clazz0 = String.class;
		KRRDialectType<String> wrappedDialectType0 = new AbstractKRRDialectType<String>(
				dialectTypeName0, dialect0, clazz0) {
		};
		String wrappedValue0 = "Test Manifestation Zero";
		DialectTypeEnvironment environment0 = new HashKRRDialectTypeEnvironment(
				wrappedDialectType0);
		BasicKnowledgeManifestation manifestation0 = new WrapperBasicKnowledgeManifestation(
				dialect0, wrappedDialectType0, wrappedValue0, environment0) {
		};

		String wrappedValue1 = "Test Manifestation One";
		BasicKnowledgeManifestation manifestation1 = new WrapperBasicKnowledgeManifestation(
				dialect0, wrappedDialectType0, wrappedValue1, environment0) {
		};

		return Arrays.asList(new Object[][] { { manifestation0, dialect0, wrappedDialectType0, wrappedValue0 },
				{ manifestation1, dialect0, wrappedDialectType0, wrappedValue1 } });
	}
}
