package concepts.implementations.pubsub;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PubSubWithBlockingQueue {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);

        Runnable producer = () -> {
            int count = 0;
            while (true) {
                try {
                    blockingQueue.put("message "+count);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(producer);

        thread1.start();
        thread2.start();

        Runnable consumer = () -> {
            int count = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread4 = new Thread(consumer);
        Thread thread5 = new Thread(consumer);
        Thread thread6 = new Thread(consumer);

        thread4.start();
        thread5.start();
        thread6.start();



    }




}
