package concepts.threads.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for(int i=0;i<1000;i++) {
            final int x = i;
            executorService.execute(() -> {
                AdderServiceContext.userId.set("user"+x);
                AdderServiceContext.commonVar="user"+x;
                int ans = AdderServiceContext.threadLocal.get().add(x,x+12);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(AdderServiceContext.userId.get() + " " + ans + " " + AdderServiceContext.commonVar);
            });
        }

        executorService.shutdown();

        while(!executorService.isTerminated()) {}

        System.out.println("Execution done");
    }
}

