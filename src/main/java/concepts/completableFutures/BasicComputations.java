package concepts.completableFutures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicComputations {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Processing inputs...");
        },executorService);
        completableFuture.get();

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Processing inputs...");
            return 2;
        },executorService);
        System.out.println(completableFuture2.get());



        CompletableFuture completableFuture3 = CompletableFuture.completedFuture("Future value");
        System.out.println(completableFuture3.get());

        CompletableFuture complexFuture = CompletableFuture.allOf(completableFuture);
        System.out.println(complexFuture.get());


        executorService.shutdown();



    }
}
