package api4kbj;

public class KRRLanguagePair {
	public KRRLanguagePair(KRRLanguage first, KRRLanguage second) {
		this.first = first;
		this.second = second;
	}

	private KRRLanguage first;
	private KRRLanguage second;

	KRRLanguage _1() {
		return first;
	}

	KRRLanguage _2() {
		return second;
	}

}