package api4kbj;

/**
 * Generic Interface for API4KB codec (code/decode) systems.
 * 
 * @author taraathan
 *
 * @param <T>
 *            the decoded type
 * @param <S>
 *            the encoded type
 */
public interface CodecSystem<T, S> {

	/**
	 * 
	 * @param t
	 *            the decoded item
	 * @return the encoded item
	 */
	public S code(T t);

	/**
	 * 
	 * @param s
	 *            the encoded item
	 * @return the decoded item
	 */
	public T decode(S s);

}
