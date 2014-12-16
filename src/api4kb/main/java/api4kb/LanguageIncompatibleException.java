package api4kb;

public class LanguageIncompatibleException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1044175396855660535L;

	public LanguageIncompatibleException() {
	}

	public LanguageIncompatibleException(String message) {
		super(message);
	}

	public LanguageIncompatibleException(Throwable cause) {
		super(cause);
	}

	public LanguageIncompatibleException(String message, Throwable cause) {
		super(message, cause);
	}


}
