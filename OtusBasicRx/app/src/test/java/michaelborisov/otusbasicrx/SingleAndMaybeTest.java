package michaelborisov.otusbasicrx;

import org.junit.Test;

import java.util.concurrent.Callable;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SingleAndMaybeTest {

    @Test
    // Тестируем Single::fromCallable
    public void singleFromCallableTest() throws InterruptedException {

        Single<Integer> mSingle = Single.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Callable::call: " + Thread.currentThread().getName());
                return 5;
            }
        }).subscribeOn(Schedulers.computation());

        mSingle
                //.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .doOnSuccess(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("SingleObserver::doOnNext: " + Thread.currentThread().getName());
                    }
                })
                .subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("SingleObserver::onSubscribe");
            }

            @Override
            public void onSuccess(Integer integer) {
                System.out.println("SingleObserver::onSuccess: " + Thread.currentThread().getName());
                System.out.println("Received value: " + integer.toString());
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        Thread.sleep(3000);

    }

    @Test
    // Тестируем Single::create
    public void singleFromJustTest() throws InterruptedException {

        Single<Integer> mSingle = Single.just(12);

        Disposable d = mSingle.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Received value: " + integer.toString());
            }
        });

        Thread.sleep(3000);
    }

    @Test
    // Тестируем Single::create
    public void singleCreateTest() throws InterruptedException {

        Single<Integer> mSingle = Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                emitter.onError(new IllegalArgumentException());
            }
        });

        Disposable d = mSingle.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Received value: " + integer.toString());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("Caught exception: ");
                throwable.printStackTrace();
            }
        });

        Thread.sleep(3000);
    }


    @Test
    // Тестируем Maybe::fromCallable
    public void maybeFromCallableTest() throws InterruptedException {
        Maybe<Integer> mMaybe = Maybe.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        });

        mMaybe.subscribe(new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {
                System.out.println("Received value: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    @Test
    // Тестируем Maybe.empty()
    public void maybeEmptyTest() throws InterruptedException {
        Maybe<Integer> mMaybe = Maybe.empty();

        mMaybe.subscribe(new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {
                System.out.println("Received value: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }


}
