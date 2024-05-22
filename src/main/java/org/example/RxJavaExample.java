package org.example;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaExample {
    public static void main(String[] args) {
        Observable<Integer> observable= Observable.create(emitter->{
            try
            {
                for(int i=0;i<5;i++) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(i);
                        Thread.sleep(1000);
                    }
                }
                emitter.onComplete();
            }
            catch (Exception e)
            {
                emitter.onError(e);
            }
        });
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(
                        item -> System.out.println("Received: " + item),
                        error -> System.err.println("Error: " + error), // onError
                        () -> System.out.println("Done!")
                );

        try{
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}