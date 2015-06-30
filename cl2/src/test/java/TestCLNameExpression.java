import cl2.CLName;
import cl2.CLPrefix;

/**
 * @author ralph
 *
 */
public class TestCLNameExpression {

	/**
	 * 
	 */
	public TestCLNameExpression() {
		
		CLPrefix[] prefixes = new CLPrefix[] {
				new CLPrefix("", "http://example.org/import.xcl#"), 
				new CLPrefix("ex", "http://example.org/")
		};
		
		CLName name = new CLName("ex:text.xcl");
	}

}
