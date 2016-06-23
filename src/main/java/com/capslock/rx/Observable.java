package com.capslock.rx;

/**
 * Created by alvin.
 */
public class Observable<T> {
    private OnSubscribe<T> onSubscribe;

    public interface OnSubscribe<T> extends Action1<Subscriber<? super T>> {
    }

    public interface Operator<R, T> extends Func1<Subscriber<? super R>, Subscriber<? super T>> {
    }

    public Observable(final OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> f) {
        return new Observable<T>(f);
    }

    public <R> Observable<R> map(Func1<T, R> transform) {
        return lift(new OperatorMap<>(transform));
    }

    private <R> Observable<R> lift(final Operator<? extends R, ? super T> operator) {
        return new Observable<>(subscriber -> {
            Subscriber<? super T> st = operator.call(subscriber);
            onSubscribe.call(st);
        });
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
