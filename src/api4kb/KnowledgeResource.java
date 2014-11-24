package api4kb;

public interface KnowledgeResource extends ImmutableSource, KnowledgeSource {

	// provides a canonical String serialization of the KnowledgeResource
	String toString();
	
	// Only possible in Java 1.8 to define these methods in the interface
	// determines if a KnowledgeResource is equal to this one
	//default Boolean equals(KnowledgeResource that) {
	//	return this.toString().equals(that);
	//}
	
	//
	//default int hashCode() {
	//	return this.toString().hashCode();
	//}

}
