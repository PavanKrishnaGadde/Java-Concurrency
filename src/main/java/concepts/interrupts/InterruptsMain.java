package concepts.interrupts;

import concepts.threads.synchronization.Printer;

public class InterruptsMain {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new IOProcessor());
        thread1.start();
        Thread.sleep(1000);
        thread1.interrupt();

        //---------------------------------------

        Thread thread2 = new Thread(new ComplexProcessor());
        thread2.start();
        Thread.sleep(10);
        thread2.interrupt();

        //---------------------------------------

        Thread thread3 = new Thread(new ResumingProcessor());
        thread3.start();
        Thread.sleep(10);
        thread3.interrupt();
        Thread.sleep(10);
        thread3.interrupt();
        Thread.sleep(10);
        thread3.interrupt();

        //------------------------------------------

        Printer printer = new Printer(30);

        Thread thread4 = new Thread(() -> {
            try {
                printer.printEvenNumbers();
            } catch (InterruptedException e) {
               System.out.println("Interrupt occurred in printer..");
            }
        });

        thread4.start();
        Thread.sleep(1000);
        thread4.interrupt();

    }

}
