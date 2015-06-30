package cl2;

import cl2a.CLExpressionLike;

public class CLPrefix extends CLExpressionLike {

	public CLPrefix(String pre, String iri) {
		super();
		this.pre = pre;
		this.iri = iri;
	}

	private String pre;
	private String iri;

	public String pre() {
		return pre;
	}

	public String iri() {
		return iri;
	}

}
