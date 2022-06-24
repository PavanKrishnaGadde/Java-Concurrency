package concepts.threads.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsMain {

    public static AdderService adderService = new AdderService();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for(int i=0;i<1000;i++) {
            final int x = i;
            executorService.execute(() -> {
                System.out.println(adderService.add(x,x+12));
            });
        }

        executorService.shutdown();

        while(!executorService.isTerminated()) {}

        System.out.println("Execution done");
    }
}
