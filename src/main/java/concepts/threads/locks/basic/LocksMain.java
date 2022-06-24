package concepts.threads.locks.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocksMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        Printer printer = new Printer();
        for(int i=0;i<100;i++) {
            int x = i;
            executorService.execute(() -> {
                try {
                    printer.printSync("message"+x);
//                    printer.print("message"+x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}
