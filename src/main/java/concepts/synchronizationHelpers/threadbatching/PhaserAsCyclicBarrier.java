package concepts.synchronizationHelpers.threadbatching;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserAsCyclicBarrier {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new Service(phaser,300));
        executorService.submit(new Service(phaser,1500));
        executorService.submit(new Service(phaser,3500));
        executorService.shutdown();
    }

    public static class Service implements Runnable {
        private Phaser phaser;
        private int delay;
        public Service(Phaser phaser, int delay){
            this.phaser = phaser;
            this.delay = delay;
        }

        @Override
        public void run() {
            System.out.println("Service started...");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Initialisation complete after " + delay + " ms");

            phaser.arriveAndAwaitAdvance();
            System.out.println("Proceeding with other operations");
        }
    }



}