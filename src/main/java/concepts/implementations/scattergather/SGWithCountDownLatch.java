package concepts.implementations.scattergather;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SGWithCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        Set<Double> prices = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new PriceAggregatorWithLatch(prices,"amazon", countDownLatch));
        executorService.submit(new PriceAggregatorWithLatch(prices,"walmart", countDownLatch));
        executorService.submit(new PriceAggregatorWithLatch(prices,"instamart", countDownLatch));
        executorService.shutdown();

        //ignore results that come after 2 seconds timeout
        countDownLatch.await(2, TimeUnit.SECONDS);
        System.out.println(prices);
    }
}

