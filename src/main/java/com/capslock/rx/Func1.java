package com.capslock.rx;

/**
 * Created by alvin.
 */
public interface Func1<T, R> extends Function {
    R call(T t);
}
