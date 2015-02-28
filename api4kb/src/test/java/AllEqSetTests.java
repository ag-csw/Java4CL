import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fj.F;
import fj.Ord;
import fj.data.Set;
import functional.EqSet;
import functional.None;
import functional.Option;
import functional.Some;
import api4kba.AbstractKRRLanguage;
import api4kbc.EqSetKnowledgeExpression;
import api4kbc.EqSetStructuredKnowledgeExpression;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;

@RunWith(Suite.class)
@SuiteClasses({ EqSetTest.class //
})
public class AllEqSetTests {

	public static AbstractKRRLanguage lang0 = new LangA("Language Zero",
			TestEqSetKE0.class) {
	};
	public static AbstractKRRLanguage lang1 = new LangA("Language One",
			TestEqSetKE1.class) {
	};
	public static AbstractKRRLanguage lang2 = new LangB("Language Two",
			TestEqSetKE2.class) {
	};

	public static Option<TestEqSetKE0> arg0 = new None<TestEqSetKE0>();
	public static TestEqSetKE0 expression0 = new TestEqSetKE0(AllTests.str,
			arg0);

	public static TestEqSetKE1 expression1a = new TestEqSetKE1(AllTests.stra);
	public static Option<TestEqSetKE1> arg1 = new Some<TestEqSetKE1>(
			expression1a);
	public static TestEqSetKE1 expression1 = new TestEqSetKE1(AllTests.str,
			arg1);

	public static TestEqSetKE2 expression2a = new TestEqSetKE2(AllTests.stra);
	public static Option<TestEqSetKE2> arg2b = new Some<TestEqSetKE2>(
			expression2a);
	public static TestEqSetKE2 expression2b = new TestEqSetKE2(AllTests.strb,
			arg2b);
	public static Option<TestEqSetKE2> arg2 = new Some<TestEqSetKE2>(
			expression2b);
	public static TestEqSetKE2 expression2 = new TestEqSetKE2(AllTests.str,
			arg2);

	public static Ord<EqSetKnowledgeExpression> exprOrder = Ord.hashEqualsOrd();

	public static EqSet<KRRLanguage> languages0 = EqSet.eqSet(lang0, lang1);
	public static EqSet<EqSetKnowledgeExpression> expressions0 = EqSet.eqSet(
			expression0, expression1);
	public static EqSetStructuredKnowledgeExpression stexpr0 = EqSetStructuredKnowledgeExpression
			.ke(languages0, expressions0);

	public static Set<KRRLanguage> fjlanguages1 = Set.set(Ord.hashEqualsOrd(),
			lang1, lang2);
	public static EqSet<KRRLanguage> languages1 = EqSet.eqSet(fjlanguages1);
	public static Set<EqSetKnowledgeExpression> fjexpressions1 = Set.set(
			Ord.hashEqualsOrd(), expression1, expression2);
	public static EqSet<EqSetKnowledgeExpression> expressions1 = EqSet.eqSet(
			expression1, expression2);
	public static EqSetStructuredKnowledgeExpression stexpr1 = EqSetStructuredKnowledgeExpression
			.ke(expressions1);

	public static EqSet<KRRLanguage> languages2 = EqSet.eqSet(lang0, lang1,
			lang2);
	public static Set<EqSetKnowledgeExpression> fjexpressions2 = Set.set(
			Ord.hashEqualsOrd(), stexpr0, stexpr1);
	public static EqSet<EqSetKnowledgeExpression> expressions2 = EqSet
			.eqSet(fjexpressions2);
	public static EqSetStructuredKnowledgeExpression stexpr2 = EqSetStructuredKnowledgeExpression
			.ke(expressions2);

	public static EqSetStructuredKnowledgeExpression stexpr3 = EqSetStructuredKnowledgeExpression
			.join(stexpr2);
	public static EqSetStructuredKnowledgeExpression stexpr4 = EqSetStructuredKnowledgeExpression
			.join(stexpr3);

	public static EqSet<EqSetKnowledgeExpression> singleton1 = EqSet
			.unit(expression1);
	public static EqSet<EqSetKnowledgeExpression> singleton2 = EqSet
			.unit(expression2);

	public static F<EqSetKnowledgeExpression, EqSet<EqSetKnowledgeExpression>> G1 = s -> EqSet
			.eqSet(s, AllEqSetTests.expression1);
	public static F<EqSetKnowledgeExpression, EqSet<EqSetKnowledgeExpression>> G2 = s -> EqSet
			.eqSet(s, AllEqSetTests.expression2);
	public static F<EqSet<EqSetKnowledgeExpression>, EqSet<EqSet<EqSetKnowledgeExpression>>> H1 = s -> EqSet
			.eqSet(s, expressions1);
	public static F<EqSet<EqSetKnowledgeExpression>, EqSet<EqSet<EqSetKnowledgeExpression>>> H2 = s -> EqSet
			.eqSet(s, expressions2);

}

class TestEqSetKE0 extends TestEqSetKE {

	TestEqSetKE0(String value) {
		this(value, new None<TestEqSetKE0>());
	}

	TestEqSetKE0(String value, Option<TestEqSetKE0> arg) {
		super(value.toLowerCase(), AllEqSetTests.lang0, arg);
	}

}

class TestEqSetKE1 extends TestEqSetKE {

	TestEqSetKE1(String value) {
		this(value, new None<TestEqSetKE1>());
	}

	TestEqSetKE1(String value, Option<TestEqSetKE1> arg) {
		super(value, AllEqSetTests.lang1, arg);
	}

}

class TestEqSetKE2 extends TestEqSetKE {

	TestEqSetKE2(String value) {
		this(value, new None<TestEqSetKE2>());
	}

	TestEqSetKE2(String value, Option<TestEqSetKE2> arg) {
		super(value.toUpperCase(), AllEqSetTests.lang2, arg);
	}

}

abstract class TestEqSetKE extends EqSetKnowledgeExpression implements
		BasicKnowledgeExpression {

	private final KRRLanguage lang;
	private final String symbol;
	private final Option<? extends TestEqSetKE> arg;

	TestEqSetKE(String value, KRRLanguage lang) {
		this(value, lang, new None<TestEqSetKE>());
	}

	TestEqSetKE(String value, KRRLanguage lang,
			Option<? extends TestEqSetKE> arg) {
		super(EqSet.eqSet(lang));
		this.lang = lang;
		this.symbol = value;
		this.arg = arg;
	}

	@Override
	public KRRLanguage language() {
		return lang;
	}

	public String symbol() {
		return symbol;
	}

	public Option<? extends TestEqSetKE> arg() {
		return arg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arg == null) ? 0 : arg.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestEqSetKE other = (TestEqSetKE) obj;
		if (arg == null) {
			if (other.arg != null)
				return false;
		} else if (!arg.equals(other.arg))
			return false;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

}