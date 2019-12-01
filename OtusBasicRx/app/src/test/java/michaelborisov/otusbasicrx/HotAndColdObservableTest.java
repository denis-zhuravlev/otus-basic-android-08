package michaelborisov.otusbasicrx;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class HotAndColdObservableTest {


    @Test
    // Тестируем Cold observables
    public void observableTest() throws InterruptedException {
        final Observable<String> mObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("ObservABLE::subscribe: " + Thread.currentThread().getName());
                emitter.onNext("First");
                emitter.onNext("Second");
            }
        }).subscribeOn(Schedulers.computation());

        mObservable
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("doOnNext: " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<String>() {

                    Disposable d;

                    @Override
                    public void onSubscribe(Disposable d) {
                        this.d = d;
                        System.out.println("ObservER::onSubscribe: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("ObservER::onNext: " + Thread.currentThread().getName());
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(3000);
    }

    @Test
    // Тестируем ковертирование Cold в Hot observable с помощью ConnectableObservable
    public void coldToHotObservableTestWithConnectableObservable() throws InterruptedException {
        final Observable<String> mObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("ObservABLE::subscribe: " + Thread.currentThread().getName());
                emitter.onNext("First");
                emitter.onNext("Second");
            }
        }).subscribeOn(Schedulers.computation());
        ConnectableObservable<String> mConnectableObservable = mObservable.publish();
        mConnectableObservable.connect();
        mConnectableObservable
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<String>() {

                    Disposable d;

                    @Override
                    public void onSubscribe(Disposable d) {
                        this.d = d;
                        System.out.println("ObservER::onSubscribe: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("ObservER::onNext: " + Thread.currentThread().getName());
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Thread.sleep(3000);
    }

    @Test
    // Тестируем ковертирование Cold в Hot observable с помощью ConnectableObservable
    public void coldToHotObservableTestWithSubject() throws InterruptedException {
        final Observable<String> mObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("ObservABLE::subscribe: " + Thread.currentThread().getName());
                emitter.onNext("First");
                emitter.onNext("Second");
            }
        }).subscribeOn(Schedulers.computation());
        PublishSubject<String> publishSubject = PublishSubject.create();
        mObservable.subscribe(publishSubject);

        Thread.sleep(3000);
    }

    @Test
    // Тестируем ковертирование Cold в Hot observable с помощью Subject. Не рекомендуется
    public void coldToHotObservableInjectNewValues() throws InterruptedException {
        final Observable<String> mObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                System.out.println("ObservABLE::subscribe: " + Thread.currentThread().getName());
                emitter.onNext("First");
                emitter.onNext("Second");
            }
        }).subscribeOn(Schedulers.computation());
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<String>() {

                    Disposable d;

                    @Override
                    public void onSubscribe(Disposable d) {
                        this.d = d;
                        System.out.println("ObservER::onSubscribe: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("ObservER::onNext: " + Thread.currentThread().getName());
                        System.out.println("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        mObservable.subscribe(publishSubject);
        publishSubject.onNext("Third");

        Thread.sleep(3000);
    }


    @Test
    public void subjectTest() throws InterruptedException {
        final PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.onNext("First");
        publishSubject
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("doOnNext: " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.computation())
                .subscribe(new Observer<String>() {

                    Disposable d;

                    @Override
                    public void onSubscribe(Disposable d) {
                        this.d = d;
                        System.out.println("ObservER::onSubscribe: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("ObservER::onNext: " + Thread.currentThread().getName());
                        System.out.println("onNext: " + s);
                        //this.d.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        publishSubject.onNext("Second");
        publishSubject.onNext("Third");

        Thread.sleep(3000);
    }
}
