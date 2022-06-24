package concepts.synchronizationHelpers.threadbatching;

import concepts.implementations.scattergather.PriceAggregatorWithLatch;
import sun.awt.windows.ThemeReader;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchMain {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new Service(countDownLatch));
        executorService.submit(new Service(countDownLatch));
        executorService.submit(new Service(countDownLatch));
        executorService.shutdown();

        countDownLatch.await();
        System.out.println("All services completed");
    }

    public static class Service implements Runnable{

        CountDownLatch countDownLatch;
        public Service(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            //process the request
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("processing done");
            countDownLatch.countDown();
        }
    }
}

