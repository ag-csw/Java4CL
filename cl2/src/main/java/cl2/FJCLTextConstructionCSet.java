/**
 * 
 */
package cl2;

import java.util.Iterator;

import fj.F;
import fj.data.List;
import functional.EqEither;
import functional.EqSet;
import static fj.Function.curry;

/**
 * @author tara
 *
 */
public class FJCLTextConstructionCSet<A extends CLSentenceOrStatementOrText> extends CLSentenceOrStatementOrText implements Iterable<A> {

	private FJCLTextConstructionCSet(final EqSet<CLCommentExpression> comments,
			final List<EqEither<A, FJCLTextConstructionCSet<A>>> argsList) {
		this.comments = comments;
		this.argsList = argsList;
	}

	final private EqSet<CLCommentExpression> comments;
	final private List<EqEither<A, FJCLTextConstructionCSet<A>>> argsList;

	// factory methods

	public static <B extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<B> 
	empty() {
		EqSet<CLCommentExpression> emptycomments = EqSet.empty();
		List<EqEither<B, FJCLTextConstructionCSet<B>>> components = List.nil();
		return new FJCLTextConstructionCSet<B>(emptycomments, components);
	}

	public static <B  extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<B> 
	text (List<EqEither<B, FJCLTextConstructionCSet<B>>> components) {
		EqSet<CLCommentExpression> emptycomments = EqSet.empty();
		return new FJCLTextConstructionCSet<B>(emptycomments, components);
	}

	public static <B  extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<B> 
	fromLeftList (List<B> contents) {
		EqSet<CLCommentExpression> emptycomments = EqSet.empty();
		return new FJCLTextConstructionCSet<B>(emptycomments, contents.map(s -> EqEither.unitLeft(s)));
	}

	public static <B  extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<B> 
	text (EqSet<CLCommentExpression> comments, List<EqEither<B, FJCLTextConstructionCSet<B>>> components) {
		return new FJCLTextConstructionCSet<B>(comments, components);
	}

	public static <B  extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<B> 
	unit(B expressionb){
		EqSet<CLCommentExpression> emptycomments = EqSet.empty();
		return unit(emptycomments, expressionb);
	}

	public static <B  extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<B> 
	unit(EqSet<CLCommentExpression> comments, B expressionb){
		EqEither<B,  FJCLTextConstructionCSet<B>> eitherb = EqEither.unitLeft(expressionb);
		return new 
				FJCLTextConstructionCSet<B>(comments, 
				   List.cons(
				      eitherb, List.nil()));
	}

	// extract component set for an M[B] tree from a node of an M[M[B]] tree
	// maintaining left/right orientation
	private static <B  extends CLSentenceOrStatementOrText> List<EqEither<B, FJCLTextConstructionCSet<B>>> 
	extract(EqEither<FJCLTextConstructionCSet<B>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<B>>> eitherb){
		if (eitherb.isLeft()) {
			FJCLTextConstructionCSet<B> eitherbleft = eitherb.left();
			return eitherbleft.argsList().map(s -> insertComments(eitherbleft.comments, s));
		}
		return List.cons(EqEither.unitRight( join( eitherb.right() )), List.nil());
	}
	
	public static <B  extends CLSentenceOrStatementOrText>
	EqEither<B, FJCLTextConstructionCSet<B>>
	insertComments(EqSet<CLCommentExpression> comments, 
			EqEither<B, FJCLTextConstructionCSet<B>> eitherb) {
		EqEither<B, FJCLTextConstructionCSet<B>> result;
		if(eitherb.isLeft()) {
			B eitherbleft = eitherb.left();
			B newexpressionb = (B) eitherbleft.insertComments(comments);
		    result = EqEither.unitLeft(newexpressionb);
		    return result;
		}
		result = EqEither.unitRight(eitherb.right().insertComments(comments));
		return result;
	}
	
	@Override
	public FJCLTextConstructionCSet<A> 
	insertComments(EqSet<CLCommentExpression> incomments) {
		return text( comments.union(incomments), argsList);		
	}
		
	public FJCLTextConstructionCSet<A> 
	insertLeaf(A expressiona) {
		return text( comments, List.cons( EqEither.unitLeft(expressiona) ,argsList));		
	}
		
	public FJCLTextConstructionCSet<A>
	insertBranch(FJCLTextConstructionCSet<A> t) {
			return text(List.cons(EqEither.unitRight(t), argsList));
	}

	public List<EqEither<A, FJCLTextConstructionCSet<A>>> 
	argsList() {
		return argsList;
	}

	public static <B extends CLSentenceOrStatementOrText> List<EqEither<B, FJCLTextConstructionCSet<B>>> 
	argsList(
			FJCLTextConstructionCSet<B> textb) {
		return textb.argsList;
	}

	// first-class version of argsList(x)
	public static <B extends CLSentenceOrStatementOrText> F<FJCLTextConstructionCSet<B>, List<EqEither<B, FJCLTextConstructionCSet<B>>>> 
	argsList_() {
		return s -> argsList(s);
	}

	public EqSet<CLCommentExpression> 
	comments() {
		return comments;
	}

	public static <B extends CLSentenceOrStatementOrText> EqSet<CLCommentExpression> 
	comments(
			FJCLTextConstructionCSet<B> textb) {
		return textb.comments();
	}

	public Boolean 
	contains(Object m) {
		return this.toLeftList().exists(s -> s.equals(m));
	}
	
	public boolean 
	containsAll(final Iterable<?> c) {
		for (Object m : c) {
			if (!contains(m))
				return false;
		}
		return true;
	}

	public List<A> 
	toLeftList() {
		return flatten().argsList().map(EqEither::left);
	}
	
	@Override
	public Iterator<A> 
	iterator() {
		return flatten().iterator();
	}
	
	public int 
	length() {
		return flatten().argsList.length();
	}
	
	public static <B extends CLSentenceOrStatementOrText> EqEither<FJCLTextConstructionCSet<B>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<B>>> 
	level(final EqEither<B, FJCLTextConstructionCSet<B>> x) {
			if (x.isLeft()) return EqEither.unitLeft((unit(x.left())));
			return EqEither.unitLeft(x.right());
	}
	
	public boolean 
	isFlat() {
		return EqEither.isAllLeft(argsList);
	}
	
	public static <B extends CLSentenceOrStatementOrText>  FJCLTextConstructionCSet<B> 
	flatten(FJCLTextConstructionCSet<B> textb){
		if (textb.isFlat()) return textb;
		return flatten( join(FJCLTextConstructionCSet.text(textb.comments, textb.argsList.map(s -> level(s)))));
	}
	
	public FJCLTextConstructionCSet<A> 
	flatten(){
		return flatten(this);
	}
	


	// Monad methods
	// unit method
	// first-class versions of unit
	public static <B extends CLSentenceOrStatementOrText> F<B, FJCLTextConstructionCSet<B>> 
	unit_() {
		return b -> unit(EqSet.eqSet(), b);
	}

	public static <B extends CLSentenceOrStatementOrText> F<B, FJCLTextConstructionCSet<B>> 
	unit_(
			EqSet<CLCommentExpression> comments) {
		return b -> unit(comments, b);
	}

	// join method
	// Must satisfy join(unit(y)) = y for y in the monad
	public static <B  extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<B> 
	join( FJCLTextConstructionCSet<FJCLTextConstructionCSet<B>> texttextb){
		return new 
				FJCLTextConstructionCSet<B>(texttextb.comments(), 
				   texttextb.argsList().bind(s -> extract(s))); 
	}

	// first-class version of join(x)
	public static <B extends CLSentenceOrStatementOrText> F<FJCLTextConstructionCSet<FJCLTextConstructionCSet<B>>, FJCLTextConstructionCSet<B>> 
	join_() {
		return s -> join(s);
	}

	
	// extract value from a node of the tree and map
	private static <B  extends CLSentenceOrStatementOrText, C  extends CLSentenceOrStatementOrText> 
	EqEither<C, FJCLTextConstructionCSet<C>> 
	extractmap(EqEither<B, FJCLTextConstructionCSet<B>> intext, F< B,  C> G){
		if (intext.isLeft()) {
			return	EqEither.unitLeft(
					     G.f(
							intext.left()));
		}
		return EqEither.unitRight(
					  intext.right().map(G));
	}

	// map method
	// Test: bind(s -> unit(f.f(s))) = map(f)
	// Test: must preserve composition:
	// assertEquals( x.map(g).map(f) , map(f.compose(g)) );
	// Test: must preserve identity:
	// assertEquals ( x.map(s -> s) , x )
	public <B extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<B> 
	map(F< A,  B> G) {
		return text(comments, argsList.map(s -> extractmap(s, G)));
	}

	// static version of map
	public static <B extends CLSentenceOrStatementOrText, 
	               C extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<C>  
	map(F<B, C> G, FJCLTextConstructionCSet<B> intext) {
		return intext.map(G);
	}

	// first-class version of map
	public static <B extends CLSentenceOrStatementOrText, C extends CLSentenceOrStatementOrText> F<F<B, C>, 
	F<FJCLTextConstructionCSet<B>, FJCLTextConstructionCSet<C>>> 
	map_() {
		return curry((G, textb) -> textb.map(G));
	}

	// bind method
	public <B extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<B> 
	bind(F<A, FJCLTextConstructionCSet<B>> G) {
		return join(map(G));
	}

	// static version of bind
	public static <B extends CLSentenceOrStatementOrText, C extends CLSentenceOrStatementOrText> FJCLTextConstructionCSet<C> 
	bind( F<B, FJCLTextConstructionCSet<C>> G, FJCLTextConstructionCSet<B> textb) {
		return textb.bind(G);
	}

	// first-class version of bind
	public static <B extends CLSentenceOrStatementOrText, C extends CLSentenceOrStatementOrText> F<F<B, FJCLTextConstructionCSet<C>>, F<FJCLTextConstructionCSet<B>, FJCLTextConstructionCSet<C>>> 
	bind_() {
		return curry((G, textb) -> textb.bind(G));
	}


	@Override
	public int hashCode() {
		int h = 0;
		Iterator<CLCommentExpression> i = comments.iterator();
		while (i.hasNext()) {
			CLCommentExpression obj = i.next();
			if (obj != null)
				h += obj.hashCode();
		}
		Iterator<EqEither<A, FJCLTextConstructionCSet<A>>> i1 = argsList.iterator();
		while (i1.hasNext()) {
			EqEither<A, FJCLTextConstructionCSet<A>> obj = i1.next();
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
		//if (o.hashCode() != this.hashCode())
		//	return false;
		if (!(o instanceof FJCLTextConstructionCSet))
			return false;
		FJCLTextConstructionCSet<?> c = (FJCLTextConstructionCSet<?>) o;
		if (c.length() != length())
			return false;
		try {
			if (!comments.equals(c.comments))
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

	

}
