package com.capslock.rx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alvin.
 */
public class OperatorObserveOn<T> implements Observable.Operator<T, T> {
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new ObserveOnSubscriber<>(subscriber);
    }

    private static final class ObserveOnSubscriber<T> extends Subscriber<T> implements Action0 {
        private static final ExecutorService service = Executors.newSingleThreadExecutor();
        final Subscriber<? super T> subscriber;

        public ObserveOnSubscriber(final Subscriber<? super T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void onCompleted() {
            service.submit(subscriber::onCompleted);
        }

        @Override
        public void onError(final Throwable e) {
            service.submit(() -> subscriber.onError(e));
        }

        @Override
        public void onNext(final T t) {
            service.submit(() -> subscriber.onNext(t));
        }

        @Override
        public void call() {

        }
    }
}
