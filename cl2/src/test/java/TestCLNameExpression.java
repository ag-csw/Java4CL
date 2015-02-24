import cl2.CLName;
import cl2.CLPrefixExpression;

/**
 * @author ralph
 *
 */
public class TestCLNameExpression {

	/**
	 * 
	 */
	public TestCLNameExpression() {
		
		CLPrefixExpression[] prefixes = new CLPrefixExpression[] {
				new CLPrefixExpression("", "http://example.org/import.xcl#"), 
				new CLPrefixExpression("ex", "http://example.org/")
		};
		
		CLName name = new CLName("ex:text.xcl");
	}

}
