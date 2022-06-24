package concepts.threads.synchronization;

public class Printer {
    private int number;
    private final int LIMIT;
    public Printer(int limit) {
        number = 0;
        LIMIT = limit;
    }

    public void printOddNumbers() throws InterruptedException {
        synchronized (this) {
            while(number<LIMIT) {
                if (number%2==0) {
                    wait();
                }
                System.out.println("ODD: " + number);
                number++;
                notifyAll();
            }
        }

    }

    public synchronized void printEvenNumbers() throws InterruptedException {
        while(number<LIMIT) {
            if (number%2!=0) {
                wait();
            }
            System.out.println("EVEN: " + number);
            number++;
            notify();
        }
    }

}
