package com.capslock.rx;

/**
 * Created by alvin.
 */
public interface Observer<T> {
    void onCompleted();

    void onError(Throwable e);

    void onNext(T t);
}
