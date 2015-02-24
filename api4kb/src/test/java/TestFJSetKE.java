import fj.Ord;
import fj.data.Set;
import api4kbc.FJSetKnowledgeExpression;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;



public abstract class TestFJSetKE extends FJSetKnowledgeExpression implements BasicKnowledgeExpression {

		private final String symbol;
		private final KRRLanguage lang;

		TestFJSetKE(String value, KRRLanguage lang) {
			super(Set.set(Ord.hashEqualsOrd() , lang));
			this.symbol = value;
			this.lang = lang;
		}

		public String symbol() {
			return symbol;
		}
		
		@Override
		public KRRLanguage language(){
			return lang;
		}


	}
