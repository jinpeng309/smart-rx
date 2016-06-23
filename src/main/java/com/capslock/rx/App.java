package com.capslock.rx;

/**
 * Created by alvin.
 */
public class App {
    public static void main(String[] args) {
        Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    public void call(final Subscriber<? super Integer> subscriber) {
                        System.out.println("current thread " + Thread.currentThread().hashCode());
                        subscriber.onNext(3);
                    }
                })
                .subscribeOn()
                .observableOn()
                .map(value -> "hi," + value)
                .subscribe((x) -> {
                    System.out.println("current thread " + Thread.currentThread().hashCode());
                    System.out.println(x);
                });
    }
}
