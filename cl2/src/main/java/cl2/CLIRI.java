package cl2;

import org.apache.abdera.i18n.iri.IRI;

public class CLIRI {

	private final IRI iri;

	public CLIRI(String datatypestring) {
		this.iri = new IRI(datatypestring);
	}

	public IRI getIri() {
		return iri;
	}
	
	@Override
	public String toString(){
		return iri.toString();
	}

}
