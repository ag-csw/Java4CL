import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;


public abstract class TestKE implements BasicKnowledgeExpression {

		private final String symbol;
		private final KRRLanguage lang;

		TestKE(String value, KRRLanguage lang) {
			this.symbol = value;
			this.lang = lang;
		}

		public String symbol() {
			return symbol;
		}

		@Override
		public KRRLanguage language() {
			return lang;
		}

	}
