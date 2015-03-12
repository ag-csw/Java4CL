package functional;

import static fj.Function.curry;

import java.util.function.Function;

import fj.F;
import fj.data.Either;

public class EqEither<A, B> {

	
	public EqEither(Either<A, B> value) {
		super();
		this.value = value;
	}

	private Either<A,B> value;
	
	public static <C, D> EqEither<C, D> unitLeft(C x){
		return new EqEither<C, D>(Either.left(x));
	}
	
	public static <C, D> EqEither<C, D> unitRight(D x){
		return new EqEither<C, D>(Either.right(x));
	}
	
	public boolean isLeft(){
		return value.isLeft();
	}
	
	public boolean isRight(){
		return value.isRight();
	}

	public static <B, C> boolean isAllLeft(Iterable<EqEither<B, C>> c){
		for(EqEither<B, C> m : c){
			if(m.isRight()) return false;
		}
		return true;
	}

	public static <B, C> boolean isAllRight(Iterable<EqEither<B, C>> c){
		for(EqEither<B, C> m : c){
			if(m.isLeft()) return false;
		}
		return true;
	}
	
	public A left(){
		return value.left().value();
	}

	public B right(){
		return value.right().value();
	}
	
	public <C> C bimap(Function<A, C> h, Function<B, C> f){
		if (isLeft()) return h.apply(left());
		return f.apply(right());
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (value.isLeft()) {
		  result = prime * result + ((value == null) ? 0 : value.left().hashCode());
		}
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EqEither))
			return false;
		final EqEither<?,?> other = (EqEither<?,?>) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (value.isLeft() != other.value.isLeft()) {
			return false;
		} else if (value.isRight() != other.value.isRight()) {
			return false;
		} else if ((value.isLeft()) && (!value.left().value().equals(other.value.left().value()) )) {
			return false;
		} else if ((value.isRight()) && (!value.right().value().equals(other.value.right().value()) )) {
			return false;
		}
		return true;
	}
	
	


}
