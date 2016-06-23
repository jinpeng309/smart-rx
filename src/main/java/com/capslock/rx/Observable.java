package com.capslock.rx;

/**
 * Created by alvin.
 */
public class Observable<T> {
    private OnSubscribe<T> onSubscribe;

    public interface OnSubscribe<T> extends Action1<Subscriber<? super T>> {
    }

    public Observable(final OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> f) {
        return new Observable<T>(f);
    }

    public void subscribe(final Action1<T> onNext) {
        final Subscriber<T> subscriber = new Subscriber<T>() {
            public void onCompleted() {

            }

            public void onError(final Throwable e) {

            }

            public void onNext(final T t) {
                onNext.call(t);
            }
        };
        subscribe(subscriber);
    }

    private void subscribe(final Subscriber<T> subscriber) {
        subscribe(subscriber, this);
    }

    private static <T> void subscribe(final Subscriber<T> subscriber, Observable<T> observable) {
        observable.onSubscribe.call(subscriber);
    }
}
