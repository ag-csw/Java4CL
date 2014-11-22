package api4kb;

public interface Dialect<T> {
	
	// check for language(s) the dialect is compatible with
    Boolean isCompatibleWith(Language language);
}
