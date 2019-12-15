package michaelborisov.otusbasicrx;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;


public class Scheduling {

    @Test
    public void trampolineTest() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.trampoline())
                .subscribe(integer -> System.out.println("Received: " + integer));
        Observable.just(5, 6, 7, 8, 9)
                .subscribeOn(Schedulers.trampoline())
                .subscribe(integer -> System.out.println("Received: " + integer));
    }

    private Observable<Long> doInts(TestScheduler testScheduler) {
        return Observable.interval(1L, 5L, TimeUnit.SECONDS, testScheduler);
    }

    @Test
    public void doIntervals() throws InterruptedException {

        TestScheduler testScheduler = new TestScheduler();


        TestObserver<Long> testObserver = doInts(testScheduler)
                .subscribeOn(testScheduler)
                .observeOn(testScheduler)
                .test();

        testObserver.assertNotTerminated()
                .assertNoErrors()
                .assertValueCount(0);

        testScheduler.advanceTimeBy(1L, TimeUnit.SECONDS);
        testObserver.assertValueCount(1);
        testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS);
        testObserver.assertValueCount(2);
        testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS);
        testObserver.assertValueCount(3);

        testObserver.dispose();
    }

    @Test
    public void testSubscriberTestExample() {

        TestScheduler testScheduler = new TestScheduler();

        Observable<Integer> observable = Observable.just(6).delay(1, TimeUnit.SECONDS, testScheduler);

        TestObserver<Integer> testObserver = observable.test();

        testScheduler.advanceTimeBy(950, TimeUnit.MILLISECONDS);
        testObserver.assertNotTerminated();
        testScheduler.advanceTimeBy(60, TimeUnit.MILLISECONDS);
        testObserver.assertValue(6);
        testObserver.assertComplete();

        testObserver.dispose();
    }
}
