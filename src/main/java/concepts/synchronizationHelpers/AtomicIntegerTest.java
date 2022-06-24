package concepts.synchronizationHelpers;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static void main(String[] args) throws InterruptedException {
        final int[] number = {0};
        AtomicInteger atomicInteger = new AtomicInteger(0);

        Thread thread1 = new Thread(() -> {
                for(int i=0;i<1000;i++) {
                    number[0]++;
                    atomicInteger.getAndIncrement();
                }
            });

        Thread thread2 =new Thread(() -> {
                for(int i=0;i<1000;i++) {
                    number[0]++;
                    atomicInteger.getAndIncrement();
                }
            });

        Thread thread3 =new Thread(() -> {
            for(int i=0;i<1000;i++) {
                number[0]++;
                atomicInteger.getAndIncrement();
            }
        });
        Thread thread4 =new Thread(() -> {
            for(int i=0;i<1000;i++) {
                number[0]++;
                atomicInteger.getAndIncrement();
            }
        });

        thread1.start();;
        thread2.start();
        thread3.start();
        thread4.start();


        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        System.out.println("Integer: " + number[0] + " Atomic Integer: " + atomicInteger);

    }
}
