package api4kb;

import lazykb.LazyInitializing;

public abstract class AbstractKnowledgeResource implements KnowledgeResource,
		LazyInitializing<KnowledgeResource> {
	// package-private constructor for non-lazy initialization
	AbstractKnowledgeResource() {
	}

	// package-private constructor for lazy initialization
	AbstractKnowledgeResource(AbstractKnowledgeResource initialValue) {
		this.initialValue = initialValue;
	}

   // cache for lazy initialization
	protected AbstractKnowledgeResource initialValue;
	
    // public clear cache method for forgetting initializing value
	// which can be called after some eager evaluation has been performed.
	// Implementing classes must override this method to include
	// verification that object will not be left in an inconsistent state.
	// After verification, the unsafeClearInitialValue method can be called.
	@Override
	public abstract void clearInitialValue();
	
	void unsafeClearInitialValue() {
		initialValue = null;		
	}


    // package-private getter for direct access to cache
	public AbstractKnowledgeResource getInitialValue() {
		return initialValue;
	}



}