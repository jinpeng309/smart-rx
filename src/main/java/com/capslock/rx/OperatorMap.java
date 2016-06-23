package com.capslock.rx;

/**
 * Created by alvin.
 */
public final class OperatorMap<T, R> implements Observable.Operator<R, T> {
    private final Func1<? super T, ? extends R> transform;

    public OperatorMap(final Func1<? super T, ? extends R> transform) {
        this.transform = transform;
    }

    @Override
    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        return new Subscriber<T>() {
            public void onCompleted() {
                subscriber.onCompleted();
            }

            public void onError(final Throwable e) {
                subscriber.onError(e);
            }

            public void onNext(final T t) {
                subscriber.onNext(transform.call(t));
            }
        };
    }
}
