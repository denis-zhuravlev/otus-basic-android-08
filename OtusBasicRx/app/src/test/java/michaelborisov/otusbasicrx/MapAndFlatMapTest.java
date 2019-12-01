package michaelborisov.otusbasicrx;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MapAndFlatMapTest {

    @Test
    public void mapTest() throws InterruptedException {
        Observable<String> mObservable = Observable.fromArray("First", "Second", "Third", "Fourth")
                .subscribeOn(Schedulers.newThread());

        Disposable d = mObservable.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                if(s.equals("First")){
                    return 1;
                }
                if(s.equals("Second")){
                    return 2;
                }
                if(s.equals("Third")){
                    return 3;
                }

                return 0;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer s) throws Exception {
                System.out.println(s.toString());
            }
        });
        Thread.sleep(3000);
    }

    @Test
    public void flatMapTest() throws InterruptedException {
        Observable<String> mObservable = Observable.fromArray("First", "Second", "Third", "Fourth")
                .subscribeOn(Schedulers.newThread());

        Disposable d = mObservable.flatMap(new Function<String, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(final String s) throws Exception {
                return new ObservableSource<Integer>() {
                    @Override
                    public void subscribe(Observer<? super Integer> observer) {
                        //observer.onNext(0);
                        if(s.equals("First")){
                            observer.onNext(1);
                        }
                        if(s.equals("Second")){
                            observer.onNext(2);
                            observer.onNext(2);
                        }
                        if(s.equals("Third")){
                            observer.onNext(3);
                            observer.onNext(3);
                            observer.onNext(3);
                        }

                    }
                };
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Received: " + integer.toString());
            }
        });
    }
}
