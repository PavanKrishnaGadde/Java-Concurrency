package concepts.synchronizationHelpers.threadbatching;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserMain {
    public static void main(String[] args) {

        Phaser phaser = new Phaser(1);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(new MultiRequest(3, phaser,300, "req1"));
        executorService.submit(new MultiRequest(3, phaser,1500, "req2"));
        executorService.submit(new MultiRequest(3, phaser,3500, "req3"));
        executorService.submit(new SingleRequest(phaser, 400, "sr1"));
        executorService.submit(new SingleRequest(phaser, 600, "sr1"));

        executorService.shutdown();
        int phase = phaser.arrive();
        phaser.awaitAdvance(phase);
        System.out.println("passed the phase " + phase);

        phase = phaser.arrive();
        phaser.awaitAdvance(phase);
        System.out.println("passed the phase " + phase);

        phase = phaser.arrive();
        phaser.awaitAdvance(phase);
        System.out.println("passed the phase " + phase);
    }

    public static class MultiRequest implements Runnable {
        private int noOfRequests;
        private Phaser phaser;
        private int delay;
        private String name;

        public MultiRequest(int noOfRequests, Phaser phaser, int delay, String name) {
            this.noOfRequests = noOfRequests;
            this.phaser = phaser;
            this.delay = delay;
            this.name = name;
            phaser.register();
        }


        @Override
        public void run() {
            for(int i=0;i<noOfRequests;i++) {
                System.out.println("Request started.." + i + " from " + name);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Request Sent " + i + name);
                phaser.arriveAndAwaitAdvance();
                System.out.println("processing next " + i + name);
            }
        }
    }

    public static class SingleRequest implements Runnable {
        private Phaser phaser;
        private int delay;
        private String name;

        public SingleRequest(Phaser phaser, int delay, String name) {
            this.phaser = phaser;
            this.delay = delay;
            this.name = name;
            phaser.register();
        }


        @Override
        public void run() {
            System.out.println("Single Request started.. from" + name);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Single Request Sent " + name);
            phaser.arriveAndDeregister();
            System.out.println("Single Request de-registered" + name);
        }
    }
}
