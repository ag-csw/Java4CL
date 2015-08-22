package api4kbop;

import api4kbj.Mapping;

public interface UnaryOperation<T, R> extends Operation<R>, Mapping<T, R> {

}
