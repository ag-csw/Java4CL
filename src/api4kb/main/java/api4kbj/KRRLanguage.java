package api4kbj;

public interface KRRLanguage {

	public String getName();

	// TODO
	// public KRRLogic getLogic();

	public KRRDialect defaultDialect();

	public KRRDialectType<?> defaultDialectType();

}
