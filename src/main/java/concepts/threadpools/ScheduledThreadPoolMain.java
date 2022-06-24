package concepts.threadpools;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolMain {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        executorService.schedule(new Task("scheduledTask"),5, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(new Task("repeatedTask"),3, 3, TimeUnit.SECONDS);

        executorService.scheduleWithFixedDelay(new Task("delayedRepeatedTask"),2,4,TimeUnit.SECONDS);

        System.out.println("Tasks are running as per schedule");

        Thread thread = new Thread(new IODriver());
        thread.start();
        //Timeout above thread after 5 seconds
        executorService.schedule(()-> thread.interrupt(), 5, TimeUnit.SECONDS);


    }

    public static class IODriver implements Runnable {

        @Override
        public void run() {
            System.out.println("Connecting to IO");
            //IO Operations
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                System.out.println("Cancel requested.. stopping thread");
                return;
            }
            System.out.println("IO Operations completed");
        }
    }
}
