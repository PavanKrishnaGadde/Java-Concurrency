package concepts.synchronizationHelpers.threadbatching;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierMain {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> System.out.println("All gathered - Barrier broken"));

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i=0;i<12;i++) {
            int x = i;
            executorService.execute(() -> {
                System.out.println("Hello from thread " + x);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("Bye from thread " + x);
            });
        }

        executorService.shutdown();

    }
}
