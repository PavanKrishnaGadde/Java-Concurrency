package concepts.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CallableMain {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<Integer>> futures = new ArrayList<>();

        for(int i=0;i<10;i++) {
           Future<Integer> future  = executorService.submit(new Task());
           futures.add(future);
        }


        List<Integer> list = futures.stream().map(future -> {
            Integer num = 0;
            try {
                num = future.get(10000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            return num;
        }).collect(Collectors.toList());

        list.stream().distinct().forEach(System.out::println);

        System.out.println(list.stream().count());

        System.out.println(list.stream().max((a,b) -> a-b).orElse(0));

        System.out.println(list.stream().limit(5).distinct().count());
        System.out.println(list.stream().filter(x -> x>0).collect(Collectors.toList()));

        Future<Integer> future  = executorService.submit(new Task());

        if(future.isDone()) {
            System.out.println("Task Done");
        }

        try {
            Integer integer = future.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(future.isDone()) {
            System.out.println("Task Done now");
        }


        executorService.shutdown();

    }
}
