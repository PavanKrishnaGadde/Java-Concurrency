package concepts.threads.locks.conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PubSubService {
    List<String> messages;
    private final int LIMIT;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Condition empty = reentrantLock.newCondition();
    private final Condition full = reentrantLock.newCondition();

    public PubSubService(int limit) {
        LIMIT = limit;
        messages = new ArrayList<>();
    }

    public void publish(String message) throws InterruptedException {
        reentrantLock.lock();
        try {
            while (messages.size()>LIMIT) {
                System.out.println("Queue full " + messages.size());
                full.await();
            }
            messages.add(message);
            empty.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public String consume() throws InterruptedException {
        reentrantLock.lock();
        String m="";
        try {
            while (messages.size()==0) {
                System.out.println("Queue Empty " + messages.size());
                empty.await();
            }
             m = messages.remove(0);
            full.signalAll();
        } finally {
            reentrantLock.unlock();
        }
        return m;
    }


}
