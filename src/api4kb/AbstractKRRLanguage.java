package api4kb;

public abstract class AbstractKRRLanguage implements KRRLanguage {

	public AbstractKRRLanguage(String name) {
		this.name = name;
		// this.logic = logic;
	}

	private final String name;
	// private final Logic logic;

	public String getName() {
		return name;
	}
	// TODO
	//public KRRLogic getLogic(){
	//	return logic;
	//}
			
}
