import hashenvironment.HashFocusedKRRLanguageEnvironment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fj.Ord;
import fj.data.Set;
import functional.None;
import functional.Option;
import functional.Some;
import api4kba.AbstractKRRLanguage;
import api4kbc.FJSetKnowledgeExpression;
import api4kbc.FJSetStructuredKnowledgeExpression;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;

@RunWith(Suite.class)
@SuiteClasses({ 
    //StructuredKnowledgeExpressionTest.class//
   })
public class AllFJSetTests {
	
	
	
	public static AbstractKRRLanguage lang0 = new LangA("Language Zero", TestFJSetKE0.class){
	};
	public static AbstractKRRLanguage lang1 = new LangA("Language One", TestFJSetKE1.class){
	};
	public static AbstractKRRLanguage lang2 = new LangB("Language Two", TestFJSetKE2.class){
	};

	
    public static Ord<KRRLanguage> langOrder = Ord.hashEqualsOrd();

	
	public static HashFocusedKRRLanguageEnvironment env0 = new HashFocusedKRRLanguageEnvironment(lang0);
	public static HashFocusedKRRLanguageEnvironment env1 = new HashFocusedKRRLanguageEnvironment(lang1);
	public static HashFocusedKRRLanguageEnvironment env2 = new HashFocusedKRRLanguageEnvironment(lang2);

		
	public static Option<TestFJSetKE0> arg0 = new None<TestFJSetKE0>();
	public static TestFJSetKE0 expression0 = new TestFJSetKE0(AllTests.str, arg0);
	
	public static TestFJSetKE1 expression1a = new TestFJSetKE1(AllTests.stra);
	public static Option<TestFJSetKE1> arg1 = new Some<TestFJSetKE1>(expression1a);
	public static TestFJSetKE1 expression1 = new TestFJSetKE1(AllTests.str, arg1);
	
	public static TestFJSetKE2 expression2a = new TestFJSetKE2(AllTests.stra);
	public static Option<TestFJSetKE2> arg2b = new Some<TestFJSetKE2>(expression2a);
	public static TestFJSetKE2 expression2b = new TestFJSetKE2(AllTests.strb, arg2b);
	public static Option<TestFJSetKE2> arg2 = new Some<TestFJSetKE2>(expression2b);
	public static TestFJSetKE2 expression2 = new TestFJSetKE2(AllTests.str, arg2);
	
	
	public static Ord<FJSetKnowledgeExpression> exprOrder  = Ord.hashEqualsOrd();

	public static Set<KRRLanguage> languages0 = Set.set(langOrder , AllTests.lang0, AllTests.lang1);
	public static Set<FJSetKnowledgeExpression> expressions0 = Set.set(exprOrder, expression0, expression1);
	public static FJSetStructuredKnowledgeExpression stexpr0 = FJSetStructuredKnowledgeExpression.ke(languages0 , expressions0);

	public static Set<KRRLanguage> languages1 = Set.set(langOrder , lang1, lang2);
	public static Set<FJSetKnowledgeExpression> expressions1 = Set.set(exprOrder, expression1, expression2);
	public static FJSetStructuredKnowledgeExpression stexpr1 = FJSetStructuredKnowledgeExpression.ke(expressions1);

	public static Set<KRRLanguage> languages2 = Set.set(langOrder , lang0, lang1, lang2);
	public static Set<FJSetKnowledgeExpression> expressions2 = Set.set(exprOrder, stexpr0, stexpr1);
	public static FJSetStructuredKnowledgeExpression stexpr2 = FJSetStructuredKnowledgeExpression.ke(expressions2);

	public static FJSetStructuredKnowledgeExpression stexpr3 = FJSetStructuredKnowledgeExpression.join(stexpr2);
	public static FJSetStructuredKnowledgeExpression stexpr4 = FJSetStructuredKnowledgeExpression.join(stexpr3);

}
class TestFJSetKE0 extends TestFJSetKE {

	TestFJSetKE0(String value) {
		this(value, new None<TestFJSetKE0>());
	}

	TestFJSetKE0(String value, Option<TestFJSetKE0> arg) {
		super(value.toLowerCase(), AllFJSetTests.lang0, arg);
	}


}
class TestFJSetKE1 extends TestFJSetKE {

	TestFJSetKE1(String value) {
		this(value, new None<TestFJSetKE1>());
	}

	TestFJSetKE1(String value, Option<TestFJSetKE1> arg) {
		super(value, AllFJSetTests.lang1, arg);
	}


}

class TestFJSetKE2 extends TestFJSetKE {

	TestFJSetKE2(String value) {
		this(value, new None<TestFJSetKE2>());
	}

	TestFJSetKE2(String value, Option<TestFJSetKE2> arg) {
		super(value.toUpperCase(), AllFJSetTests.lang2, arg);
	}


}


abstract class TestFJSetKE extends FJSetKnowledgeExpression implements BasicKnowledgeExpression {

	private final KRRLanguage lang;
	private final String symbol;
	private final Option<? extends TestFJSetKE> arg;

	TestFJSetKE(String value, KRRLanguage lang) {
		this(value, lang, new None<TestFJSetKE>());
	}
	
	TestFJSetKE(String value, KRRLanguage lang, Option<? extends TestFJSetKE> arg) {
		super(Set.set(AllFJSetTests.langOrder, lang));
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

	public Option<? extends TestFJSetKE> arg() {
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
		TestFJSetKE other = (TestFJSetKE) obj;
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