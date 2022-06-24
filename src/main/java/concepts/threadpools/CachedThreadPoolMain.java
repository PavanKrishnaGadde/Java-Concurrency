package concepts.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++) {
            executorService.execute(new Task("task"+i));
        }

        executorService.shutdown();
    }
}
