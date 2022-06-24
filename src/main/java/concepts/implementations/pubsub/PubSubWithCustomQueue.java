package concepts.implementations.pubsub;

public class PubSubWithCustomQueue {

    public static void main(String[] args) {
//        CustomBlockingQueueWithoutLocks<String> blockingQueue = new CustomBlockingQueueWithoutLocks<>(5);
        CustomBlockingQueue<String> blockingQueue = new CustomBlockingQueue<>(5);

        Runnable producer = () -> {
            int count = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blockingQueue.put("message " + count);
                count++;
            }
        };

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(producer);

        thread1.start();
        thread2.start();

        Runnable consumer = () -> {
            int count = 0;
            while (true) {
                System.out.println(blockingQueue.take());
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

