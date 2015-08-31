/**
 * 
 */
package cl2fj;

import cl2a.CLCommentSet;
import cl2a.CLExpression;
import cl2.CLTextConstruction;

/**
 * @author tara
 *
 */
public class CLTextConstructionfj<A extends CLExpression>
		extends CLTextConstruction {
	
	private CLExpressionSetfj<A> texts;

    public CLTextConstructionfj(
			CLCommentSet comments,
			CLExpressionSetfj<A> texts) {
		super(comments, texts);
	}


	@Override
	public CLTextConstructionfj<A> insertComments(CLCommentSet incomments) {
		return new CLTextConstructionfj<A>( comments().concat(incomments), 
				texts);
	}

	@Override
	public CLTextConstructionfj<A> copy() {
		return new CLTextConstructionfj<A>(
				comments().copy(),
				texts.copy());
	}


	/*
	
	private FJCLTextConstruction(
			final List<A> argsList,
			 CLCommentExpression... comments
			) {
		this.argsList = argsList;
		this.comments = comments;
	}

	private CLCommentExpression[] comments;
	final private List<A> argsList;

	// factory methods

	public static <B extends CLExpression> FJCLTextConstruction<B> 
	empty() {
		return new FJCLTextConstruction<B>(List.nil(), List.nil());
	}

	public static <B extends CLExpression> FJCLTextConstruction<B> 
	text(
			final List<B> argsList) {
		return new FJCLTextConstruction<B>(List.nil(), argsList);
	}

	public static <B extends CLExpression> FJCLTextConstruction<B> 
	text(
			final List<CLCommentExpression> commentsList, final List<B> argsList) {
		return new FJCLTextConstruction<B>(commentsList, argsList);
	}

	public List<A> argsList() {
		return argsList;
	}

	public static <B extends CLExpression> List<B> argsList(
			FJCLTextConstruction<B> x) {
		return x.argsList;
	}

	// first-class version of argsList(x)
	public static <B extends CLExpression> F<FJCLTextConstruction<B>, List<B>> argsList_() {
		return s -> argsList(s);
	}

	public List<CLCommentExpression> commentsList() {
		return commentsList;
	}

	public static <B extends CLExpression> List<CLCommentExpression> commentsList(
			FJCLTextConstruction<B> x) {
		return x.commentsList();
	}

	public boolean contains(final Object m) {
		for (A x : argsList) {
			if (x == m)
				return true;
			if ((x != null) && (x.equals(m)))
				return true;
		}
		return false;
	}

	public boolean containsAll(final Iterable<?> c) {
		for (Object m : c) {
			if (!contains(m))
				return false;
		}
		return true;
	}

	public int length() {
		return argsList.length();
	}

	// Monad methods
	// unit method
	public static <B extends CLExpression> FJCLTextConstruction<B> unit(
			B x) {
		return text(List.nil(), List.single(x));
	}

	public static <B extends CLExpression> FJCLTextConstruction<B> unit(
			List<CLCommentExpression> commentsList, B x) {
		return text(commentsList, List.single(x));
	}

	// first-class version of unit
	// assertEquals(unit_().apply(x), unit(x))
	public static <B extends CLExpression> F<B, FJCLTextConstruction<B>> unit_() {
		return b -> unit(List.nil(), b);
	}

	public static <B extends CLExpression> F<B, FJCLTextConstruction<B>> unit_(
			List<CLCommentExpression> commentsList) {
		return b -> unit(commentsList, b);
	}

	// flatten method
	public static <B extends CLExpression> FJCLTextConstruction<B> flatten(
			FJCLTextConstruction<B> x) {
		return text(
				x.commentsList().append(
						x.argsList().bind(s -> flattenComments(s))), x
						.argsList().bind(s -> flattenArgs(s)));
	}

	// join method
	// Must satisfy join(unit(unit(x))) = unit(x)
	public static <B extends CLExpression> FJCLTextConstruction<B> join(
			FJCLTextConstruction<FJCLTextConstruction<B>> x) {
//		return text(
//		x.commentsList().append(
//				x.argsList().bind(s -> flattenComments(s))), x
//				.argsList().bind(s -> argsList(s)));
		@SuppressWarnings("unchecked")
		FJCLTextConstruction<B> result = (FJCLTextConstruction<B>) flatten(x);
		return result;
	}

	// first-class version of join(x)
	public static <B extends CLExpression> F<FJCLTextConstruction<FJCLTextConstruction<B>>, FJCLTextConstruction<B>> join_() {
		return s -> join(s);
	}

	private static <B extends CLExpression> List<B> flattenArgs(
			B x) {
		if (x instanceof FJCLTextConstruction) {
			@SuppressWarnings("unchecked")
			final FJCLTextConstruction<CLExpression> y = (FJCLTextConstruction<CLExpression>) x;
			if (y.length() == 0)
				return List.nil();
			try {
				@SuppressWarnings("unchecked")
				final List<B> yargs = (List<B>) y.argsList();
				return yargs;
			} catch (ClassCastException unused) {
				return List.single(x);
			}
		}
		return List.single(x);
	}

	private static List<CLCommentExpression> flattenComments(
			CLExpression x) {
		if (x instanceof FJCLTextConstruction) {
			@SuppressWarnings("unchecked")
			final FJCLTextConstruction<CLExpression> y = (FJCLTextConstruction<CLExpression>) x;
			return y.commentsList();
		}
		return List.nil();
	}

	// bind method
	public <B extends CLExpression> FJCLTextConstruction<B> bind(
			F<A, FJCLTextConstruction<B>> f) {
		return join(map(f));

	}

	// static version of bind
	// Test: bind(f, x) = x.bind(f)
	public static <B extends CLExpression, C extends CLExpression> FJCLTextConstruction<C> bind(
			F<B, FJCLTextConstruction<C>> f, FJCLTextConstruction<B> x) {
		return x.bind(f);
	}

	// first-class version of bind
	// Test: bind_().apply(f).apply(x) = x.bind(f)
	public static <B extends CLExpression, C extends CLExpression> F<F<B, FJCLTextConstruction<C>>, F<FJCLTextConstruction<B>, FJCLTextConstruction<C>>> bind_() {
		return curry((f, as) -> as.bind(f));
	}

	// map method
	// Test: bind(s -> unit(f.f(s))) = map(f)
	// Test: must preserve composition:
	// assertEquals( x.map(g).map(f) , map(f.compose(g)) );
	// Test: must preserve identity:
	// assertEquals ( x.map(s -> s) , x )
	public <B extends CLExpression> FJCLTextConstruction<B> map(
			F<A, B> f) {
		return text(commentsList, argsList.map(f));
	}

	// static version of map
	// Test: map(f, x) = x.map(f)
	public static <B extends CLExpression, C extends CLExpression> FJCLTextConstruction<C> map(
			F<B, C> f, FJCLTextConstruction<B> x) {
		return x.map(f);
	}

	// first-class version of map
	// Test: map_().apply(f).apply(x) = x.map(f)
	public static <B extends CLExpression, C extends CLExpression> F<F<B, C>, F<FJCLTextConstruction<B>, FJCLTextConstruction<C>>> map_() {
		return curry((f, as) -> as.map(f));
	}

	public static CLExpression flattenRec(
			CLExpression x) {
		if (!(x instanceof FJCLTextConstruction))
			return x;
		@SuppressWarnings("unchecked")
		final FJCLTextConstruction<CLExpression> y = (FJCLTextConstruction<CLExpression>) x;
		return flatten(y.map(s -> flattenRec(s)));

	}

	@Override
	public int hashCode() {
		int h = 0;
		Iterator<CLCommentExpression> i = commentsList.iterator();
		while (i.hasNext()) {
			CLCommentExpression obj = i.next();
			if (obj != null)
				h += obj.hashCode();
		}
		Iterator<A> i1 = argsList.iterator();
		while (i1.hasNext()) {
			A obj = i1.next();
			if (obj != null)
				h += obj.hashCode();
		}
		return h;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this)
			return true;
		if (o == null)
			return false;
		if (o.hashCode() != this.hashCode())
			return false;
		if (!(o instanceof FJCLTextConstruction))
			return false;
		FJCLTextConstruction<?> c = (FJCLTextConstruction<?>) o;
		if (c.length() != length())
			return false;
		try {
			if (!commentsList.equals(c.commentsList))
				return false;
			if (!argsList.equals(c.argsList))
				return false;
			return true;
		} catch (ClassCastException unused) {
			return false;
		} catch (NullPointerException unused) {
			return false;
		}
	}

	@Override
	public FJCLTextConstruction<A> insertComments(CLCommentExpression... comments) {
		return new FJCLTextConstruction<A>(name, text, CL.concatComments(comments, incomments));
	}

	@Override
	public boolean isBasic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public KnowledgeSourceLevel level() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean usesLanguage(KRRLanguage language) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<CLCommentExpression> comments() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

}

