package concepts.threads.locks.basic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class Reminder implements Runnable{

    private String name;
    private ReentrantLock lock;
    private int reminderCount;
    private int waitTime;

    public Reminder(String name, ReentrantLock lock) {
        this.name = name;
        this.lock = lock;
        this.reminderCount=0;
        this.waitTime=0;
    }
    @Override
    public void run() {
        while(reminderCount < 5) {

            if(lock.tryLock()) {
                SimpleDateFormat sd = new SimpleDateFormat("hh:mm:ss");
                System.out.println(name + " Reminder " + reminderCount + " at " + sd.format(new Date()));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reminderCount++;
                lock.unlock();
            } else {
                waitTime++;
                if(waitTime%100000000==0) {
                    System.out.println(name + " waiting to acquire lock " + waitTime);
                }
            }
        }
    }
}
