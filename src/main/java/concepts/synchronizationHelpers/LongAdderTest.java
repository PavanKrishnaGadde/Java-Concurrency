package concepts.synchronizationHelpers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {
    public static void main(String[] args) throws InterruptedException {
        final int[] number = {0};
        LongAdder longAdder = new LongAdder();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(500);

        for(int i=0;i<500;i++) {
            executorService.submit(() -> {
                for(int j=0;j<100;j++) {
                    number[0]++;
                    longAdder.increment();
                    atomicInteger.getAndIncrement();
                }
            });
        }

        executorService.shutdown();
        System.out.println("Integer: " + number[0] + " Atomic Integer: " + atomicInteger + " Adder: " + longAdder.sum());

    }
}

