package concepts.implementations.pubsub;

import java.util.LinkedList;
import java.util.Queue;

public class CustomBlockingQueueWithoutLocks<E> {
    private Queue<E> queue;
    private final int CAPACITY;
    private Object notEmpty = new Object();
    private Object notFull = new Object();

    public CustomBlockingQueueWithoutLocks(int capacity) {
        CAPACITY = capacity;
        queue = new LinkedList<>();
    }

    public void put(E val) {
            try {
                synchronized (notFull) {
                    while (queue.size() >= CAPACITY) {
                        notFull.wait();
                    }
                    queue.offer(val);
                }
                synchronized (notEmpty) {
                    notEmpty.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public E take() {
        E val = null;

                try {
                    synchronized (notEmpty) {
                        while (queue.isEmpty()) {
                            notEmpty.wait();
                        }
                        val = queue.poll();
                    }

                    synchronized (notFull) {
                        notFull.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        return val;
    }


}
