package concepts.threads.synchronization;

public class NumberPrinterService {

    public static void main(String[] args) {

        Printer printer = new Printer(30);

        Thread thread1 = new Thread(){
            public void run() {
                try {
                    printer.printEvenNumbers();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread(){
            public void run() {
                try {
                    printer.printOddNumbers();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread3 = new Thread(){
            public void run() {
                try {
                    printer.printOddNumbers();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
