package concepts.implementations.scattergather;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class SGWithCF {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Set<Double> prices = Collections.synchronizedSet(new HashSet<>());


        CompletableFuture cf1 = CompletableFuture.runAsync(new PriceAggregator(prices,"amazon"));
        CompletableFuture cf2 = CompletableFuture.runAsync(new PriceAggregator(prices,"walmart"));
        CompletableFuture cf3 = CompletableFuture.runAsync(new PriceAggregator(prices,"instamart"));

        CompletableFuture allPrices = CompletableFuture.allOf(cf1,cf2,cf3);

        //ignore results that come after 2 seconds time out
        try {
            allPrices.get(2,TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("Some requests timed out");
        }

        System.out.println(prices);
    }
}

