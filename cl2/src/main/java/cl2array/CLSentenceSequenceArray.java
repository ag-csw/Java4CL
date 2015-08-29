/**
 * 
 */
package cl2array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cl2a.CLExpression;
import cl2a.CLSentence;
import cl2a.CLSentenceSequence;
import cl2a.CLExpressionSequence;

/**
 * @author ralph
 *
 */
public class CLSentenceSequenceArray extends CLSentenceSequence {

	private final CLSentence[] args;
	
	/**
	 * 
	 */
	public CLSentenceSequenceArray(CLSentence... args) {
		this.args = args;
	}

	public <T extends CLSentence> CLSentenceSequenceArray(Set<T> args) {
		this(args.toArray(new CLSentence[0]));
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
	public CLExpressionSequence concat(CLExpressionSequence inargs) {
		int bLen = inargs.length();
		CLExpression[] b= new CLSentence[bLen];
		int i = 0;
        for (final CLExpression inarg : inargs.args())
        {
            b[i++] = inarg;
        }		
        CLExpression[] c = CLArray.concatExpressions(args, b);
		return new CLExpressionSequenceArray(c);
	}

	@Override
	public CLSentenceSequence concatSentences(CLSentenceSequence inargs) {
		int bLen = inargs.length();
		CLSentence[] b= new CLSentence[bLen];
		int i = 0;
        for (final CLSentence inarg : inargs.args())
        {
            b[i++] = inarg;
        }		
        CLSentence[] c = CLArray.concatSentences(args, b);
		return new CLSentenceSequenceArray(c);
	}

	@Override
	public CLSentenceSequenceArray copy() {
		return new CLSentenceSequenceArray(CLArray.copySentences(args));
	}

}
