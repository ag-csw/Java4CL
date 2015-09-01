/**
 * 
 */
package cl2array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cl2a.CLExpression;
import cl2a.CLSentence;
import cl2a.CLSentenceSet;
import cl2a.CLExpressionSet;

/**
 * @author ralph
 *
 */
public class CLSentenceSetArray extends CLSentenceSet {

	private final CLSentence[] args;
	
	/**
	 * 
	 */
	public CLSentenceSetArray(CLSentence... args) {
		super(args);
		this.args = args;
	}

	public <T extends CLSentence> CLSentenceSetArray(Set<T> args) {
		this(args.toArray(new CLSentence[args.size()]));
	}

	@Override
	public Set<? extends CLSentence> args() {
		return new HashSet<CLSentence>(Arrays.asList(args));
	}

	@Override
	public int length(){
		return args.length;
	}

	@Override
	public CLExpressionSet concat(CLExpressionSet inargs) {
		int bLen = inargs.length();
		CLExpression[] b= new CLSentence[bLen];
		int i = 0;
        for (final CLExpression inarg : inargs.args())
        {
            b[i++] = inarg;
        }		
        CLExpression[] c = CLArray.concatExpressions(args, b);
		return new CLExpressionSetArray(c);
	}

	@Override
	public CLSentenceSet concatSentences(CLSentenceSet inargs) {
		int bLen = inargs.length();
		CLSentence[] b= new CLSentence[bLen];
		int i = 0;
        for (final CLSentence inarg : inargs.args())
        {
            b[i++] = inarg;
        }		
        CLSentence[] c = CLArray.concatSentences(args, b);
		return new CLSentenceSetArray(c);
	}

	@Override
	public CLSentenceSetArray copy() {
		return new CLSentenceSetArray(CLArray.copySentences(args));
	}

}
