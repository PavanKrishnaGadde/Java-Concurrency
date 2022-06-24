package concepts.threads.synchronization;

public class SyncPrinterService {

    public static void main(String[] args) {

        BasicPrinter printer = new BasicPrinter(30);

        Thread thread1 = new Thread(){
            public void run() {
                synchronized (printer) {
                    try {
                        printer.printEvenNumbers();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        Thread thread2 = new Thread(){
            public void run() {
                synchronized (printer) {
                    try {
                        printer.printOddNumbers();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread3 = new Thread(){
            public void run() {
                synchronized (printer) {
                    try {
                        printer.printOddNumbers();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

