package concepts.threads.locks.basic;

import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    ReentrantLock lock;

    public Printer() {
        lock = new ReentrantLock();
    }

    public void print(String message) throws InterruptedException {
        System.out.println(message);
        Thread.sleep(1000);
    }

    public void printSync(String message) throws InterruptedException {
        lock.lock();
        System.out.println(message);
        Thread.sleep(1000);
        lock.unlock();
    }
}
