package cl2;

import org.apache.abdera.i18n.iri.IRI;
import cl2i.CLImmutable;

public class CLIRI implements CLImmutable {

	private final IRI iri;

	public CLIRI(String datatypestring) {
		this.iri = new IRI(datatypestring);
	}

	public static CLIRI createCLIRI(IRI datatype) {
		return new CLIRI(datatype.toString());
	}

	public IRI iri() {
		return iri;
	}
	
	@Override
	public CLIRI copy() {
		return createCLIRI(iri);
	}

	@Override
	public String toString(){
		return iri().toString();
	}

    public boolean canEqual(Object other) {
        return (other instanceof CLIRI);
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iri == null) ? 0 : iri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLIRI))
			return false;
		CLIRI other = (CLIRI) obj;
		if (!other.canEqual(this))
			return false;
		if (iri == null) {
			if (other.iri != null)
				return false;
		} else if (!iri.equals(other.iri))
			return false;
		return true;
	}

}
