package concepts.threads.locks.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class CommonLockMain {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Reminder medicines = new Reminder("Take Medicines", lock);
        Reminder water = new Reminder("Drink Water", lock);
        Reminder exercise = new Reminder("Exercise", lock);
        Reminder sedentary = new Reminder("Sedentary", lock);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.execute(medicines);
        executorService.execute(water);
        executorService.execute(exercise);
        executorService.execute(sedentary);

        executorService.shutdown();





    }
}
