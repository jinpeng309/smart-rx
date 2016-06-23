package com.capslock.rx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alvin.
 */
public class OperatorSubscribeOn<T> implements Observable.OnSubscribe<T> {
    private final Observable<T> source;

    public OperatorSubscribeOn(final Observable<T> source) {
        this.source = source;
    }

    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            Subscriber<T> s = new Subscriber<T>() {
                @Override
                public void onCompleted() {
                    subscriber.onCompleted();
                }

                @Override
                public void onError(final Throwable e) {
                    subscriber.onError(e);
                }

                @Override
                public void onNext(final T t) {
                    subscriber.onNext(t);
                }
            };
            source.unsafeSubscribe(s);
        });
    }
}
