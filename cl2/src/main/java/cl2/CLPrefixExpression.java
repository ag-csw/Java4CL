package cl2;

import cl2a.CLExpressionLike;

public class CLPrefixExpression extends CLExpressionLike {

	public CLPrefixExpression(String pre, String iri) {
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
