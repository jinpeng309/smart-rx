package com.capslock.rx;

/**
 * Created by alvin.
 */
public class App {
    public static void main(String[] args) {
        Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    public void call(final Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(3);
                    }
                })
                .subscribeOn()
                .map(value -> "hi," + value)
                .subscribe(System.out::println);
    }
}
