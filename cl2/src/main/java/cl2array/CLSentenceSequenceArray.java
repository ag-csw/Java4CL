/**
 * 
 */
package cl2array;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cl2a.CLSentence;
import cl2a.CLSentenceSequence;
import cl2a.CLTermOrSequenceMarker;

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

	public <T extends CLSentence> CLSentenceSequenceArray(List<T> args) {
		this(args.toArray(new CLSentence[0]));
	}

	@Override
	public List<? extends CLSentence> args() {
		return Arrays.asList(args);
	}

	@Override
	public int length(){
		return args.length;
	}

	@Override
	public CLSentenceSequenceArray concat(CLSentenceSequence inargs) {
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
		return new CLSentenceSequenceArray(args);
	}

}
