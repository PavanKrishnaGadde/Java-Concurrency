package concepts.completableFutures;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChainedComputations {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        //Blocking way
        System.out.println("Blocking Start Time:" + new Date());
        for(int i=0;i<1;i++) {
            CompletableFuture<Integer> c1 = CompletableFuture.supplyAsync(() -> {
                IdGenerator idGenerator = new IdGenerator();
                return idGenerator.getNewId();
            },executorService);

            int id = 0;
            try {
                id = c1.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            int finalId = id;

            CompletableFuture<String> c2 = CompletableFuture.supplyAsync(() -> {
                EmployeeIdGenerator employeeIdGenerator = new EmployeeIdGenerator();
                return employeeIdGenerator.generateId(finalId);
            },executorService);

            String empId = null;
            try {
                empId = c2.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            System.out.println(empId);
        }
        System.out.println("Blocking End Time:" + new Date());


        //Non-blocking way
        List<CompletableFuture> futures = new ArrayList<>();
        System.out.println("Non Blocking Start Time:" + new Date());
        for(int i=0;i<1;i++) {
            futures.add(CompletableFuture.supplyAsync(() -> {
                IdGenerator idGenerator = new IdGenerator();
                return idGenerator.getNewId();
            },executorService)
                    .thenApplyAsync((id) -> {
                EmployeeIdGenerator employeeIdGenerator = new EmployeeIdGenerator();
                return employeeIdGenerator.generateId(id);
            },executorService)
                            .exceptionally((ex) -> "INVALID_ID")
                    .thenAccept(System.out::println));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
        System.out.println("Non Blocking End Time:" + new Date());

        //other functions

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
                    IdGenerator idGenerator = new IdGenerator();
                    return idGenerator.getNewId();
                },executorService)
                .thenApplyAsync((id) -> {
                    EmployeeIdGenerator employeeIdGenerator = new EmployeeIdGenerator();
                    return employeeIdGenerator.generateId(id);
                },executorService)
                .exceptionally(e -> "INVALID_ID")
                .thenApply(id -> id+"2022")
                .thenApply(id -> id+"06")
                .thenCombine(CompletableFuture.supplyAsync(() -> "S1"),
                        (sid1,sid2) -> sid1+sid2)
                .thenCompose(id -> CompletableFuture.completedFuture(id))
                        .handle((res,ex) -> {
                            if(ex != null) {
                                System.out.println(ex.getMessage());
                                return "INVALID_ID";
                            }
                            return res;
                        });

        System.out.println(cf.join());

        executorService.shutdown();

    }
}
