package concepts.callables;

import java.util.Date;
import java.util.concurrent.*;

public class CallableTasks {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Task task = new Task();
        Future<String> futureObject = executorService.submit(task);
        executorService.shutdown();
        System.out.println("executor shutting down..");
        System.out.println("Doing Other tasks..");

        try {
            System.out.println(futureObject.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("future is now..");
    }

    public static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(4000);
            return "Date is: " + new Date();
        }
    }
}
