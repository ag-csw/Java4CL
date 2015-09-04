package api4kba;

import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;
import api4kbj.StructuredKnowledgeExpression;

public abstract class AbstractStructuredKnowledgeExpression implements StructuredKnowledgeExpression {
	
	public AbstractStructuredKnowledgeExpression(Iterable<? extends KnowledgeExpression> components,
			Iterable<? extends KRRLanguage> languages) {
		super();
		this.components = components;
		this.languages = languages;
	}

	private Iterable<? extends KnowledgeExpression> components;
	private Iterable<? extends KRRLanguage> languages;
	
	public Iterable<? extends KnowledgeExpression> components() {
		return components;
	}	
	
	public Iterable<? extends KRRLanguage> languages() {
		return languages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((components() == null) ? 0 : components().hashCode());
		result = prime * result + ((languages() == null) ? 0 : languages().hashCode());
		return result;
	}

    public boolean canEqual(Object other) {
        return (other instanceof AbstractStructuredKnowledgeExpression);
    }
    
	@Override 
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractStructuredKnowledgeExpression))
			return false;
		AbstractStructuredKnowledgeExpression other = (AbstractStructuredKnowledgeExpression) obj;
		if (!other.canEqual(this))
			return false;
		if (components() == null) {
			if (other.components() != null)
				return false;
		} else if (!components().equals(other.components()))
			return false;
		if (languages() == null) {
			if (other.languages() != null)
				return false;
		} else if (!languages().equals(other.languages()))
			return false;
		return true;
	}

}