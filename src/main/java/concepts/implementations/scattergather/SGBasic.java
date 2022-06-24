package concepts.implementations.scattergather;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SGBasic {
    public static void main(String[] args) throws InterruptedException {
        Set<Double> prices = Collections.synchronizedSet(new HashSet<>());

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new PriceAggregator(prices,"amazon"));
        executorService.submit(new PriceAggregator(prices,"walmart"));
        executorService.submit(new PriceAggregator(prices,"instamart"));
        executorService.shutdown();

        //ignore results that come after 2 seconds timeout
        Thread.sleep(2000);
        System.out.println(prices);
    }
}
