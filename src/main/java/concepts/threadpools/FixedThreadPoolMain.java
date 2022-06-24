package concepts.threadpools;

import sun.security.timestamp.TSRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i=0;i<10;i++) {
            executorService.execute(new Task("task"+i));
        }

        executorService.shutdown();
    }
}
