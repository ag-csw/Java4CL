package api4kbj;

import java.util.Set;

public interface Decomposable<T> {

	// getter for components of type T
	// if empty, is basic
	// TODO change type to immutable set
	public Set<T> components();

	// check if basic
	public Boolean isBasic();

}