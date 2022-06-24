package concepts.implementations.pubsub;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomBlockingQueue<E> {
    private Queue<E> queue;
    private ReentrantLock lock;
    private Condition notFull;
    private Condition notEmpty;
    private final int CAPACITY;

    public CustomBlockingQueue(int capacity) {
        CAPACITY = capacity;
        queue = new LinkedList<>();
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void put(E val) {
        lock.lock();
        try{
            while (queue.size()>=CAPACITY) {
                notFull.await();
            }
            queue.offer(val);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public E take() {
        lock.lock();
        E val = null;
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            val = queue.poll();
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return val;
    }


}
