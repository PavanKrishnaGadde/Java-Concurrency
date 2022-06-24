package concepts.threads.basic;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPooling {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i=0;i<10;i++) {
            Runnable worker = new WorkerThread("notification" + i+i);
            executorService.execute(worker);
        }
        executorService.shutdown();
        System.out.println("Main thread processes");

        if(executorService.isShutdown()) {
            System.out.println("executor shutdown");
        }

        if(executorService.isTerminated()) {
            System.out.println("executor terminated");
        }

    }
}
