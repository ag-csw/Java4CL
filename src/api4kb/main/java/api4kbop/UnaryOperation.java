package api4kbop;

import java.util.function.Function;

public interface UnaryOperation<T, R> extends Operation, Function<T, R> {

}
