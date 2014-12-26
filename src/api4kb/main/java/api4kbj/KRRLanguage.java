package api4kbj;

public interface KRRLanguage {

	public String name();

	// TODO
	// public KRRLogic getLogic();

	public KRRDialect defaultDialect();

	public KRRDialectType<?> defaultDialectType();

}
